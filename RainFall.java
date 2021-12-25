import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;



public class RainFall extends Application {
    private TextField jan = new TextField();
    private TextField feb = new TextField();
    private TextField mar = new TextField();
    private TextField apr = new TextField();
    private Label Jan = new Label("January: ");
    private Label Feb = new Label("February: ");
    private Label Mar = new Label("March: ");
    private Label Apr = new Label("April: ");
    private Menu fileMenu;
    private Menu viewMenu;
    private MenuItem newItem;
    private MenuItem exitItem;
    private RadioMenuItem AreaChart;
    private RadioMenuItem LineChart;
    private RadioMenuItem BarChart;
    private RadioMenuItem ScatterChart;
    private BorderPane borderpane;
    private VBox vbox;
    private XYChart<String, Number> chart;


    public static void main(String[] args) {


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UnaryOperator<TextFormatter.Change> filter = text -> {
            if (text.isReplaced())
                if(text.getText().matches("[^0-9]"))
                    text.setText(text.getControlText().substring(text.getRangeStart(), text.getRangeEnd()));
            if (text.isAdded()) {
                if (text.getControlText().contains(".")) {
                    if (text.getText().matches("[^0-9]")) {
                        text.setText("");
                    }
                } else if (text.getText().matches("[^0-9.]")) {
                    text.setText("");
                }
            }
            return text;
        };
        jan.setTextFormatter(new TextFormatter<>(filter));
        feb.setTextFormatter(new TextFormatter<>(filter));
        mar.setTextFormatter(new TextFormatter<>(filter));
        apr.setTextFormatter(new TextFormatter<>(filter));
        Button submit = new Button("Submit");

        BooleanBinding boolBind = new BooleanBinding(){
            {
                super.bind(jan.textProperty(), apr.textProperty(), mar.textProperty(), feb.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (jan.getText().isEmpty() || feb.getText().isEmpty() ||
                        mar.getText().isEmpty() || apr.getText().isEmpty());
            }
        };
        submit.disableProperty().bind(boolBind);

        MenuBar menuBar = new MenuBar();
        buildFileMenu(primaryStage);
        buildViewMenu();
        menuBar.getMenus().addAll(fileMenu, viewMenu);

        Label label = new Label("Enter Inches of Rainfall For Each Month");
        GridPane gridPane = new GridPane();
        gridPane.addRow(0, Jan, jan);
        gridPane.addRow(1, Feb, feb);
        gridPane.addRow(2, Mar, mar);
        gridPane.addRow(3, Apr, apr);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(15);
        gridPane.setHgap(10);

        vbox = new VBox(10, label, gridPane, submit);
        vbox.setAlignment(Pos.CENTER);

        borderpane = new BorderPane();
        borderpane.setTop(menuBar);
        borderpane.setCenter(vbox);

        submit.setOnAction(event -> {
            if(BarChart.isSelected()){
                createBarChart();
            }
            else if(AreaChart.isSelected()) {
                createAreaChart();
            }
            else if(LineChart.isSelected()) {
                createLineChart();
            }
            else if(ScatterChart.isSelected()){
                createScatterChart();
            }
        });
        Scene newScene = new Scene(borderpane, 300,300);
        primaryStage.setScene(newScene);
        primaryStage.show();

    }
    private void createBarChart()
    {
        double janRain = Double.parseDouble(jan.getText());
        double febRain = Double.parseDouble(feb.getText());
        double marRain = Double.parseDouble(mar.getText());
        double aprRain = Double.parseDouble(apr.getText());
        CategoryAxis hAxis = new CategoryAxis();
        hAxis.setLabel( "Months");
        NumberAxis vAxis = new NumberAxis();
        vAxis.setLabel( "Rainfall");
        chart = new BarChart<>(hAxis, vAxis);
        chart.setTitle( "Rainfall January Thru April");
        XYChart.Series<String, Number> points = new XYChart.Series<>();
        points.getData().add( new XYChart.Data<>( "January", janRain));
        points.getData().add( new XYChart.Data<>( "February", febRain));
        points.getData().add( new XYChart.Data<>( "March", marRain));
        points.getData().add( new XYChart.Data<>( "April", aprRain));
        chart.getData().add(points);
        chart.setLegendVisible(false);
        borderpane.setCenter(chart);
    }
    private void createAreaChart(){
        double janRain = Double.parseDouble(jan.getText());
        double febRain = Double.parseDouble(feb.getText());
        double marRain = Double.parseDouble(mar.getText());
        double aprRain = Double.parseDouble(apr.getText());
        CategoryAxis hAxis = new CategoryAxis();
        hAxis.setLabel( "Months");
        NumberAxis vAxis = new NumberAxis();
        vAxis.setLabel( "Rainfall");
        chart = new AreaChart<>(hAxis, vAxis);
        chart.setTitle( "Rainfall Januaru Thru April");
        XYChart.Series<String, Number> points = new XYChart.Series<>();
        points.getData().add( new XYChart.Data<>( "January", janRain));
        points.getData().add( new XYChart.Data<>( "February", febRain));
        points.getData().add( new XYChart.Data<>( "March", marRain));
        points.getData().add( new XYChart.Data<>( "April", aprRain));
        chart.getData().add(points);
        chart.setLegendVisible(false);
        borderpane.setCenter(chart);
    }
    private void createLineChart(){
        double janRain = Double.parseDouble(jan.getText());
        double febRain = Double.parseDouble(feb.getText());
        double marRain = Double.parseDouble(mar.getText());
        double aprRain = Double.parseDouble(apr.getText());
        CategoryAxis hAxis = new CategoryAxis();
        hAxis.setLabel( "Months");
        NumberAxis vAxis = new NumberAxis();
        vAxis.setLabel( "Rainfall");
        chart = new LineChart<>(hAxis, vAxis);
        chart.setTitle( "Rainfall January Thru April");
        XYChart.Series<String, Number> points = new XYChart.Series<>();
        points.getData().add( new XYChart.Data<>( "January", janRain));
        points.getData().add( new XYChart.Data<>( "February", febRain));
        points.getData().add( new XYChart.Data<>( "March", marRain));
        points.getData().add( new XYChart.Data<>( "April", aprRain));
        chart.getData().add(points);
        chart.setLegendVisible(false);
        borderpane.setCenter(chart);
    }
    private void createScatterChart(){
        double janRain = Double.parseDouble(jan.getText());
        double febRain = Double.parseDouble(feb.getText());
        double marRain = Double.parseDouble(mar.getText());
        double aprRain = Double.parseDouble(apr.getText());
        CategoryAxis hAxis = new CategoryAxis();
        hAxis.setLabel( "Months");
        NumberAxis vAxis = new NumberAxis();
        vAxis.setLabel( "Rainfall");
        chart = new ScatterChart<>(hAxis, vAxis);
        chart.setTitle( "Rainfall Jan Thru Apr");
        XYChart.Series<String, Number> points = new XYChart.Series<>();
        points.getData().add( new XYChart.Data<>( "January", janRain));
        points.getData().add( new XYChart.Data<>( "February", febRain));
        points.getData().add( new XYChart.Data<>( "March", marRain));
        points.getData().add( new XYChart.Data<>( "April", aprRain));
        chart.getData().add(points);
        chart.setLegendVisible(false);
        borderpane.setCenter(chart);
    }
    private void buildViewMenu(){
        viewMenu = new Menu("View");
        AreaChart = new RadioMenuItem("Area Chart");
        LineChart = new RadioMenuItem("Line Chart");
        BarChart = new RadioMenuItem("Bar Chart (Default)");
        ScatterChart = new RadioMenuItem("Scatter Chart");
        ToggleGroup toggleGroup = new ToggleGroup();


        AreaChart.setToggleGroup(toggleGroup);
        LineChart.setToggleGroup(toggleGroup);
        BarChart.setToggleGroup(toggleGroup);
        ScatterChart.setToggleGroup(toggleGroup);
        BooleanBinding boolBind = new BooleanBinding() {
            {
                super.bind(jan.textProperty(), apr.textProperty(), mar.textProperty(), feb.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (jan.getText().isEmpty() || feb.getText().isEmpty() ||
                        mar.getText().isEmpty() || apr.getText().isEmpty());
            }
        };
        AreaChart.disableProperty().bind(boolBind);
        BarChart.disableProperty().bind(boolBind);
        LineChart.disableProperty().bind(boolBind);
        ScatterChart.disableProperty().bind(boolBind);

        BarChart.setSelected(true);

        viewMenu.getItems().addAll(BarChart, AreaChart, LineChart, ScatterChart);
        BarChart.setOnAction(event -> {
            createBarChart();
        });
        AreaChart.setOnAction(event -> {
            createAreaChart();
        });
        LineChart.setOnAction(event -> {
            createLineChart();
        });
        ScatterChart.setOnAction(event -> {
            createScatterChart();
        });


    }

    private void buildFileMenu(Stage primaryStage)
    {
        fileMenu = new Menu("File");
        newItem = new MenuItem("New");
        exitItem = new MenuItem("Exit");
        newItem.setAccelerator(KeyCombination.keyCombination("shortcut+N"));
        exitItem.setAccelerator(KeyCombination.keyCombination("shortcut+Q"));
        exitItem.setOnAction(event -> primaryStage.close());
        newItem.setOnAction(event -> {
            jan.setText("");
            feb.setText("");
            mar.setText("");
            apr.setText("");
            borderpane.setCenter(vbox);
        });
        fileMenu.getItems().addAll(newItem, exitItem);
    }

}

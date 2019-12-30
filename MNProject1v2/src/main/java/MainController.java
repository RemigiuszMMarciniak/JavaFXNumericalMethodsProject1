import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {
    private ObservableList list = FXCollections.observableArrayList();
    private double capitalE;
    private double m;
    private double x;
    private double y;
    private double period;
    private double distance;
    private double eccentricity;
    private double error;
    private String name;
    private XYChart.Series series = new XYChart.Series();
    private static ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
    private ArrayList<String> legendNames = new ArrayList<>();

    private Circle circle = new Circle();
    private Path path = new Path();
    private ArrayList<Path> pathArrayList = new ArrayList<>();
    private PathTransition pathTransition = new PathTransition();
    private ArrayList<PathTransition> pathTransitionArrayList = new ArrayList<>();

    private static ArrayList<Circle> circleArrayList = new ArrayList<>();


    public static ArrayList<Circle> getCircleArrayList() {
        return circleArrayList;
    }

    public ArrayList<Path> getPathArrayList() {
        return pathArrayList;
    }

    public static ArrayList<XYChart.Series> getSeriesArrayList() {
        return seriesArrayList;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScatterChart<Number, Number> mainScatterChart;

    @FXML
    private NumberAxis xAxisChart;

    @FXML
    private NumberAxis yAxisChart;

    @FXML
    private TextField mainTextFieldPeriod;

    @FXML
    private TextField mainTextFieldDistance;

    @FXML
    private TextField mainTextFieldEccentricity;

    @FXML
    private TextField mainTextFieldError;

    @FXML
    private ChoiceBox<String> mainChoiceBox;

    @FXML
    private Button mainButtonCalculateAndDraw;

    @FXML
    private Button mainButtonSave;

    @FXML
    private Button mainButtonAnimate;

    @FXML
    private TextField mainTextName;

    private void loadData(){
        list.removeAll(list);
        String bisectionMethod = "Bisection method";
        String falsiMethod = "Falsi method";
        String fixedPointIterationMethod = "Fixed point iteration method";
        String newtonsMethod = "Newton's method";
        String secantMethod = "Secant method";
        list.addAll(bisectionMethod,falsiMethod,fixedPointIterationMethod,newtonsMethod,secantMethod);
        mainChoiceBox.getItems().addAll(list);
    }
    @FXML
    void buttonAnimateClicked(ActionEvent event) {
          System.out.println("Animate button clicked");
        System.out.println(pathTransitionArrayList.size());
        for (int i = 0; i < pathTransitionArrayList.size(); i++) {
            pathTransitionArrayList.get(i).play();
            System.out.println(pathTransitionArrayList.get(i).getPath());
        }
        openAnimation();
    }

    @FXML
    void buttonCalculateAndDrawClicked(ActionEvent event) {
        try{
           //data import

            period = Double.parseDouble(mainTextFieldPeriod.getText());
            distance = Double.parseDouble(mainTextFieldDistance.getText());
            eccentricity = Double.parseDouble(mainTextFieldEccentricity.getText());
            error = Double.parseDouble(mainTextFieldError.getText());
            name = mainTextName.getText();
            System.out.println(period + " , " + distance + " , " + eccentricity + " , " + error + " , " + name);
            System.out.println("Data has been taken properly");

            //calculating X and Y
            double step = 0.1;
            series = new XYChart.Series();
            series.setName(name);
            legendNames.add(name);
            seriesArrayList.add(series);

            //preparing a data for an animation
            path = new Path();

            if(mainChoiceBox.getValue() == "Bisection method"){
                System.out.println(mainChoiceBox.getValue());
                for (double i = 0; i < period ; i+=step) {
                    m = i;
                    capitalE = RootSolver.bisection(0,period,error,(x) -> m + eccentricity
                            * Math.sin(x) - x);
                    x = distance * Math.cos((capitalE - eccentricity));
                    y = distance * Math.sqrt(1 - Math.pow(eccentricity,2)) * Math.sin(capitalE);
                    series.getData().add(new XYChart.Data(x , y));

                    if(i == 0){
                        path.getElements().add(new MoveTo(300+(100*x),300+(100*y)));
                    }
                    else{
                        path.getElements().add(new LineTo(300+(100*x),300+(100*y)));
                    }

                }


            }
            else if(mainChoiceBox.getValue() == "Falsi method"){
                System.out.println(mainChoiceBox.getValue());
                for (double i = 0; i < period ; i+=step) {
                    m = i;
                    capitalE = RootSolver.falsi(0,period,error,(x) -> m + eccentricity
                            * Math.sin(x) - x);
                    x = distance * Math.cos((capitalE - eccentricity));
                    y = distance * Math.sqrt(1 - Math.pow(eccentricity,2)) * Math.sin(capitalE);
                    series.getData().add(new XYChart.Data(x , y));

                    if(i == 0){
                        path.getElements().add(new MoveTo(300+(100*x),300+(100*y)));
                    }
                    else{
                        path.getElements().add(new LineTo(300+(100*x),300+(100*y)));
                    }
                }
            }
            else if(mainChoiceBox.getValue() == "Fixed point iteration method"){
                System.out.println(mainChoiceBox.getValue());
                for (double i = 0; i < period ; i+=step) {
                    m = i;
                    capitalE = RootSolver.fixedPointIteration(0,error,(x) -> m + eccentricity
                            * Math.sin(x) - x);
                    x = distance * Math.cos((capitalE - eccentricity));
                    y = distance * Math.sqrt(1 - Math.pow(eccentricity,2)) * Math.sin(capitalE);
                    series.getData().add(new XYChart.Data(x , y));

                    if(i == 0){
                        path.getElements().add(new MoveTo(300+(100*x),300+(100*y)));
                    }
                    else{
                        path.getElements().add(new LineTo(300+(100*x),300+(100*y)));
                    }
                }
            }
            else if(mainChoiceBox.getValue() == "Newton's method"){
                System.out.println(mainChoiceBox.getValue());
                for (double i = 0; i < period ; i+=step) {
                    m = i;
                    capitalE = RootSolver.newtons(0,error,eccentricity,(x) -> m + eccentricity
                            * Math.sin(x) - x);
                    x = distance * Math.cos((capitalE - eccentricity));
                    y = distance * Math.sqrt(1 - Math.pow(eccentricity,2)) * Math.sin(capitalE);
                    series.getData().add(new XYChart.Data(x , y));

                    if(i == 0){
                        path.getElements().add(new MoveTo(300+(100*x),300+(100*y)));
                    }
                    else{
                        path.getElements().add(new LineTo(300+(100*x),300+(100*y)));
                    }
                }
            }
            else if(mainChoiceBox.getValue() == "Secant method"){
                System.out.println(mainChoiceBox.getValue());
                for (double i = 0; i < period ; i+=step) {
                    m = i;
                    capitalE = RootSolver.secant(0,1,error,(x) -> m + eccentricity
                            * Math.sin(x) - x);
                    x = distance * Math.cos((capitalE - eccentricity));
                    y = distance * Math.sqrt(1 - Math.pow(eccentricity,2)) * Math.sin(capitalE);
                    series.getData().add(new XYChart.Data(x , y));

                    if(i == 0){
                        path.getElements().add(new MoveTo(300+(100*x),300+(100*y)));
                    }
                    else{
                        path.getElements().add(new LineTo(300+(100*x),300+(100*y)));
                    }
                }
            }
            else{
                System.out.println("Not selected");
                throw new NumberFormatException("Select a method!");
            }
            pathArrayList.add(path);

            //displaying on a chart

            mainScatterChart.setLegendVisible(true);
            mainScatterChart.getData().add(series);

            System.out.println(legendNames.toString());
            System.out.println(seriesArrayList.toString());

            for (int i = 0; i < seriesArrayList.size(); i++) {
                seriesArrayList.get(i).setName(legendNames.get(i));

            }

            //animation preparing

            circle = new Circle();

            circle.setFill(Color.YELLOW);
            circle.setRadius(20);
            circle.setStrokeWidth(20);

            circleArrayList.add(circle);


            pathTransition = new PathTransition();

//            path.getElements().clear();
//            path.getElements().add(new MoveTo(350,200));
//            path.getElements().add(new LineTo(400,200));
            pathTransition.setNode(circle);
            pathTransition.setPath(path);
            pathTransition.setDuration(Duration.seconds(period));
            pathTransition.setAutoReverse(false);
            pathTransition.setCycleCount(50);
            pathTransition.setOrientation(
                    PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            //    pathTransition.setCycleCount(Animation.INDEFINITE);
            pathTransitionArrayList.add(pathTransition);


        } catch(NumberFormatException e){
            e.printStackTrace();
        }
    }
    private void openSave(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/save.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Planet trajectory - save trajectory");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void openAnimation(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/animation.fxml"));
            Parent test = loader.load();

            Group root = new Group(circle);
        /*
          //Creating a Group object
        Group root = new Group(circle);

            //Creating a scene object
        Scene scene = new Scene(root, 600, 600);         */

            Stage stage = new Stage();
            stage.setScene(new Scene(root,600,600));
            stage.setTitle("Planet trajectory - animation");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void buttonSaveClicked(ActionEvent event) {
        openSave();
    }

    @FXML
    void initialize() {
        loadData();
        assert mainScatterChart != null : "fx:id=\"mainScatterChart\" was not injected: check your FXML file 'main.fxml'.";
        assert xAxisChart != null : "fx:id=\"xAxisChart\" was not injected: check your FXML file 'main.fxml'.";
        assert yAxisChart != null : "fx:id=\"yAxisChart\" was not injected: check your FXML file 'main.fxml'.";
        assert mainTextFieldPeriod != null : "fx:id=\"mainTextFieldPeriod\" was not injected: check your FXML file 'main.fxml'.";
        assert mainTextFieldDistance != null : "fx:id=\"mainTextFieldDistance\" was not injected: check your FXML file 'main.fxml'.";
        assert mainTextFieldEccentricity != null : "fx:id=\"mainTextFieldEccentricity\" was not injected: check your FXML file 'main.fxml'.";
        assert mainTextFieldError != null : "fx:id=\"mainTextFieldError\" was not injected: check your FXML file 'main.fxml'.";
        assert mainChoiceBox != null : "fx:id=\"mainChoiceBox\" was not injected: check your FXML file 'main.fxml'.";
        assert mainTextName != null : "fx:id=\"mainTextName\" was not injected: check your FXML file 'main.fxml'.";
        assert mainButtonCalculateAndDraw != null : "fx:id=\"mainButtonCalculateAndDraw\" was not injected: check your FXML file 'main.fxml'.";
        assert mainButtonSave != null : "fx:id=\"mainButtonSave\" was not injected: check your FXML file 'main.fxml'.";
        assert mainButtonAnimate != null : "fx:id=\"mainButtonAnimate\" was not injected: check your FXML file 'main.fxml'.";

    }
}
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
//        Pane test = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
//        //???
//       // root.getChildren().addAll(polygon,circle);
//
//        for (int i = 0; i < MainController.getCircleArrayList().size(); i++) {
//            test.getChildren().addAll(MainController.getPolygonArrayList().get(i),
//                    MainController.getCircleArrayList().get(i));
//        }

        Scene scene= new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Planet trajectory");
        primaryStage.show();
    }
}

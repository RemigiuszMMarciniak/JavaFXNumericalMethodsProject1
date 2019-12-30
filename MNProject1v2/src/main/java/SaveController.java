import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SaveController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField saveTextViewPath;

    @FXML
    private Text saveText;

    @FXML
    private Button saveSaveButton;

    @FXML
    private Button saveCancelButton;

    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) saveCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveToFile(ActionEvent event) {
        saveText.setText("Saving your file - it may take some time");
        String name = saveTextViewPath.getText();
        XYChart.Series toFile = new XYChart.Series();
        StringBuilder str = new StringBuilder();
        try{
            FileWriter fileWriter = new FileWriter("/home/remek/Desktop/"+name+".csv");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String temporary = new String();
            for (int j = 0; j < MainController.getSeriesArrayList().size() ; j++) {
                System.out.println(j);
                toFile = MainController.getSeriesArrayList().get(j);
                for (int i = 0; i < toFile.getData().size() ; i++) {
                    temporary = toFile.getData().get(i).toString();
                    str.append(temporary.subSequence(5,(temporary.length()-6)));
                    str.append('\n');
                    printWriter.print(str);
                }
                str.append("====");
                str.append('\n');
                str.append("====");
                str.append('\n');
                str.append("====");
                str.append('\n');
                str.append("====");
                str.append('\n');
            }

            System.out.println(str.toString());
            printWriter.close();
            saveText.setText("Saved correctly - check your desktop");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert saveTextViewPath != null : "fx:id=\"saveTextViewPath\" was not injected: check your FXML file 'save.fxml'.";
        assert saveText != null : "fx:id=\"saveText\" was not injected: check your FXML file 'save.fxml'.";
        assert saveSaveButton != null : "fx:id=\"saveSaveButton\" was not injected: check your FXML file 'save.fxml'.";
        assert saveCancelButton != null : "fx:id=\"saveCancelButton\" was not injected: check your FXML file 'save.fxml'.";

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class MainSceneController implements Initializable {

  FileChooser fileChooser = new FileChooser();
    
    private Image happyImage;
    private Image sadImage;
   
    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private GridPane gridpane; //outer gridpane

    @FXML
    private TextFlow textflow; //textflow

    @FXML
    private GridPane gridpane_inner; //inner gridpane

    @FXML
    private Button the_open_btn;

    @FXML
    void GetText(MouseEvent event) {
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MSG files (*.msg)","*.msg");
      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().add(extFilter);
      Font font_ = Font.font("Verdana", FontWeight.NORMAL, 12);
      File file = fileChooser.showOpenDialog(new Stage()); //opens file chooser
      Image happyImage = new Image("file: C:/java_project/ICA/src/pictures/smile_happy.gif");
      Image sadImage = new Image("file: C:/java_project/ICA/src/pictures/smile_sad.gif");
      gridpane.setAlignment(Pos.CENTER);
      gridpane.setPrefWidth(500); //sets the width of the outer gridpane
      gridpane.setPrefHeight(500); //sets the height of the outer gridpane

      GridPane gridpane_inner = new GridPane(); //creates inner gridpane(?)
      gridpane_inner.setPrefWidth(500); //sets the width of the inner gridpane
      gridpane_inner.setPrefHeight(500); //sets the height of the inner gridpane   
      scroll_pane.setContent(gridpane_inner); //sets the scrollpane content to the inner gridpane

      if(linecounter(file) %4 != 0){
        wrong_syntax();
      }
       //if the file is not in the correct syntax, it will display an error message
        
      else{
      //GridPane gridpane = new GridPane(); //creates gridpane
      int counter = 0; //counter for gridpane rows
      String prev_name = "";
      try{

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
            {
            String next_line = "";
            String time_String = scanner.nextLine();
            String name_String = scanner.nextLine();
            String message_String = scanner.nextLine();
            if(scanner.hasNextLine())
            {next_line = scanner.nextLine();};


            //logic for cleaning the data from the text file
            if(time_String.contains("Time:")){
                time_String =("["+ time_String.substring(5) + "] ");

                if(name_String.contains("Name:")){
                    name_String = name_String.substring(5) + ": ";

                    if (name_String.equals(prev_name)){
                        name_String = "... : ";

                    }else{
                        prev_name = name_String;

                    }
                    if(message_String.contains("Message:")){
                        message_String = message_String.substring(8);

                      if(next_line!=null){
                          next_line = "\n";
                      }

                    }else{
                        wrong_syntax();
                      }
                }else{
                    wrong_syntax();
                }
            }else{
                wrong_syntax();
            }

          if (name_String.equals("... :"))
            prev_name = name_String;

          Text text1 = new Text(time_String);
          text1.setFont(font_);

          Text text2 = new Text(name_String);
          text2.setFont(font_);
          text2.setFill(Color.BLUE);

          TextFlow textflow = new TextFlow(); //creates textflow
          textflow.setLineSpacing(5); //Setting the line spacing between the text objects
         
          textflow.setTextAlignment(TextAlignment.JUSTIFY);//Setting the line spacing between the text objects 
          
          textflow.getChildren().addAll(text1, text2);//Retrieving the observable list of the TextFlow Pane 
          var message = message_String;
          displayContent(message, textflow);

          gridpane_inner.addRow(counter, textflow); //adds the textflow to the gridpane

          counter++;
        }}
        catch (FileNotFoundException e){
        e.printStackTrace();
      }
    }
  }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      fileChooser.setInitialDirectory(new File("D:/java_class/test/src/example_msg_files"));

    }

    private void displayContent(String message, TextFlow textflow){
      Image happyImage = new Image("D:/java_class/test/src/pictures/smile_happy.gif");
      Image sadImage = new Image("D:/java_class/test/src/pictures/smile_sad.gif");
      Font font = Font.font("Verdana", FontWeight.BOLD, 12);
      String pair;
      Text textContent;
      for (int i = 0; i < message.length() - 1; ) {
          pair = message.substring(i, i + 2);
          switch (pair) {
              case ":)" -> {
                  textflow.getChildren().add(new ImageView(happyImage));
                  i += 2;
              }
              case ":(" -> {
                  textflow.getChildren().add(new ImageView(sadImage));
                  i += 2;
              }
              default -> {
                  if (i == message.length() - 2) {
                      textContent = new Text(pair);
                  } else {
                      textContent = new Text(Character.toString( message.charAt(i)));
                  }
                  textContent.setFont(font);
                  textflow.getChildren().add(textContent);
                  i++;
              }
          }
      }
  }

  public void error_message() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error");
    alert.setContentText("Wrong file format");
    alert.showAndWait();
  }
  public void wrong_syntax() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error");
    alert.setContentText("the input file is not in the correct format");
    alert.showAndWait();
  }
  public void the_file_is_empty() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error");
    alert.setContentText("the input file is empty");
    alert.showAndWait();
  }

  public int linecounter(File file) { //should check how many lines are in the file.
      int counter = 0;
      try {
          if(file.length()==0){
            the_file_is_empty();
          }
          Scanner scanner = new Scanner(file);
          String test = null;
          while (scanner.hasNextLine()) {
              counter++;
              test=scanner.nextLine();
              System.out.println(test);
              if(test.equals("")&& !scanner.hasNextLine()){ ///error here?
                wrong_syntax();
              }
          }
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      }
      counter++;
      return counter;
  }

}

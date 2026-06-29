package nedterminal;

// import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage ){

Label nedFace = new Label("<{•.•}> ");
TextArea nedText = new TextArea("Welcome back. Chlorine levels stable.");

TextField inputField = new TextField();
inputField.setStyle(
    "-fx-background-color: #121212; " +   // Macht den weissen Kasten unsichtbar
    "-fx-text-fill: #FCE300; " +              // Gleiches Gelb wie bei Neds Text
    "-fx-font-family: 'Courier New'; " +      // Gleiche Hacker-Schriftart
    "-fx-font-size: 20px; " +                 // Gleiche Grösse
    "-fx-padding: 0; " +
    "-fx-focus-color: #FCE300; -fx-faint-focus-color: #FCE300;" 
);



Integer CanvasWidth = 1000;
Integer CanvasHeight = 600;


nedText.setStyle( 
    "-fx-control-inner-background: #121212; " + 
    "-fx-background-color: transparent; " +     // Nuke den Hintergrund
    "-fx-box-border: transparent; " +
    " -fx-faint-focus-color: transparent;"  +         // Nuke den Rahmen
    "-fx-text-fill: #FCE300; " +              
    "-fx-font-family: 'Courier New'; " +      
    "-fx-font-size: 20px; " +                 
    "-fx-padding: 10px 0px 0px 0px; " +         // Ein bisschen Platz nach oben zum Gesicht
    "-fx-font-weight: bold;"
);

nedText.setEditable(false);
nedText.setFocusTraversable(false);

nedFace.setStyle("-fx-text-fill: #FCE300; -fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-font-weight: bold;");

      
        BorderPane root = new BorderPane();      
        root.setStyle("-fx-background-color: #121212;");
        root.setPadding(new Insets(30));
        root.setTop(nedFace);
        root.setCenter(nedText);
        root.setBottom(inputField);
        BorderPane.setAlignment(nedFace, Pos.CENTER);
        

     
        Scene scene = new Scene(root, CanvasWidth, CanvasHeight );

       
        primaryStage.setTitle("Ned Terminal"); 
        primaryStage.setScene(scene);
        primaryStage.show(); 

        

        inputField.setOnAction(event -> {
            
            String command = inputField.getText();

            nedText.appendText("\n> " + command + "\n");    


            if (command.equalsIgnoreCase("hello") || command.equalsIgnoreCase("hi")) {
                nedText.appendText("Ned: Hi there, nice to see you my friend");
            } else if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")) {
                nedText.appendText("Ned is going to sleep. Stay alive.");

                javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));

                delay.setOnFinished(e -> System.exit(0));
                delay.play();


            } else {
                nedText.setText("Ned is confused. Unknown command: " + command);
            }
            inputField.clear();
            
        });
    }

   

}



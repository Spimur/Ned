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
import javafx.scene.layout.BorderPane;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import javafx.application.Platform;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);

    }




    // fetch lyrics

    private void fetchLyrics(String songName, TextArea nedText) {
        String formattedSong = songName.replace(" ", "+");
        String url = "https://lrclib.net/api/search?artist_name=Twenty+One+Pilots&track_name=" + formattedSong;

    
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

      
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    String responseBody = response.body();

                    Platform.runLater(() -> {
                      
                        if (responseBody.equals("[]")) {
                            nedText.appendText("Ned couldn't find the lyrics for '" + songName + "'.\n");
                        } else {
                            try {
                              
                                String target = "\"plainLyrics\":\"";
                                int startIndex = responseBody.indexOf(target) + target.length();
                                int endIndex = responseBody.indexOf("\",\"syncedLyrics\"");
                                
                                String lyrics = responseBody.substring(startIndex, endIndex);
                                
                                lyrics = lyrics.replace("\\n", "\n").replace("\\r", "");
                                
                                nedText.appendText("\n--- " + songName.toUpperCase() + " ---\n\n" + lyrics + "\n\n----------------\n");
                            } catch (Exception e) {
                                nedText.appendText("Ned got confused reading the database.\n");
                            }
                        }
                    });
                })
                .exceptionally(e -> {
                    Platform.runLater(() -> nedText.appendText("Ned lost connection to the grid.\n"));
                    return null;
                });
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

nedText.setStyle( 
    "-fx-control-inner-background: #121212; " + 
    "-fx-background-color: #121212; " +
    "-fx-background-radius: 0; " +     // Nuke den Hintergrund
    "-fx-box-border: transparent; " +
    "-fx-faint-focus-color: transparent;"  +         // Nuke den Rahmen
    "-fx-text-fill: #FCE300; " +              
    "-fx-font-family: 'Courier New'; " +      
    "-fx-font-size: 20px; " +                 
    "-fx-padding: 10px 0px 0px 0px; " +
    "-fx-background-insets: 0; " +
    "-fx-font-weight: bold;"
);

nedFace.setStyle("-fx-text-fill: #FCE300; -fx-font-family: 'Courier New'; -fx-font-size: 30px; -fx-font-weight: bold;");



Integer CanvasWidth = 1000;
Integer CanvasHeight = 600;
String baseTextAreaStyle = "-fx-control-inner-background: #121212; -fx-background-color: #121212; -fx-background-radius: 0; -fx-box-border: transparent; -fx-padding: 10px 0 0 0; -fx-background-insets: 0; -fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: ";
String baseNedFaceStyle = "-fx-font-family: 'Courier New'; -fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: ";
String baseInputFieldStyle = "-fx-control-inner-background: #121212; -fx-background-color: #121212; -fx-background-radius: 0; -fx-box-border: transparent; -fx-faint-focus-color: transparent; -fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-padding: 10px 0px 0px 0px; -fx-background-insets: 0; -fx-font-weight: bold; -fx-text-fill: ";
String[] nedQuotes = {
    "Ned: *Sips chlorine* 'In Trench I'm not alone.'",
    "Ned: 'The sun will rise and we will try again.'",
    "Ned is grooving to the bassline.",
    "Ned: 'Peace will win and fear will lose.'",
    "Ned: 'Push on through!'",
    "Ned: 'don't just sit in silence.'",
    "Ned: 'Fight it, take the pain, ignite it'",
    "Ned: 'We've made it to another day, my friends'"
};
Random random = new Random();






nedText.setEditable(false);
nedText.setFocusTraversable(false);



      
        BorderPane root = new BorderPane();      
        root.setStyle("-fx-background-color: #121212;");
        root.setPadding(new Insets(30));
        root.setTop(nedFace);
        root.setCenter(nedText);
        root.setBottom(inputField);
        BorderPane.setAlignment(nedFace, Pos.CENTER);
        

     
        Scene scene = new Scene(root, CanvasWidth, CanvasHeight );
        scene.setOnMouseClicked(event -> inputField.requestFocus());
        nedText.setOnMouseClicked(event -> inputField.requestFocus());

       
        primaryStage.setTitle("Ned Terminal"); 
        primaryStage.setScene(scene);
        primaryStage.show(); 

        // Zwingt die versteckte ScrollPane dazu, komplett schwarz und eckig zu sein
nedText.lookup(".scroll-pane").setStyle("-fx-background-color: #121212; -fx-background-insets: 0; -fx-background-radius: 0; -fx-hbar-policy: never; -fx-vbar-policy: never;");
nedText.lookup(".viewport").setStyle("-fx-background-color: transparent;");
nedText.lookup(".content").setStyle("-fx-background-color: transparent;");

        

        inputField.setOnAction(event -> {
            
            String command = inputField.getText();

            nedText.appendText("\n> " + command + "\n");    


            if (command.equalsIgnoreCase("hello") || command.equalsIgnoreCase("hi")) {
                nedText.appendText("Ned: Hi there, nice to see you my friend");
            } else if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("bye")) {
                nedText.appendText("Ned is going to sleep. Stay alive.");

                javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));

                delay.setOnFinished(e -> System.exit(0));
                delay.play();


            } else if (command.equalsIgnoreCase("clear")) {
                nedText.clear();

            } else if (command.toLowerCase().startsWith("change style ")) {
    
    // Schneidet die ersten 6 Zeichen ("s-t-y-l-e- ") ab. Übrig bleibt z.B. "breach"
                String album = command.substring(13).toLowerCase().trim();
    
                switch (album) {
                    case "breach":
                        nedText.setStyle(baseTextAreaStyle + "#e44a3e;");
                        nedFace.setStyle(baseNedFaceStyle + "#e44a3e;");
                        inputField.setStyle(baseInputFieldStyle + "#e44a3e;");
                        break;

                    case "clancy":
                        nedText.setStyle(baseTextAreaStyle + "#D9381E;");
                        nedFace.setStyle(baseNedFaceStyle + "#D9381E;");
                        inputField.setStyle(baseInputFieldStyle + "#D9381E;");
                        break;
                    
                    case "trench":
                        nedText.setStyle(baseTextAreaStyle + "#FCE300;");
                        nedFace.setStyle(baseNedFaceStyle + "#FCE300;");
                        inputField.setStyle(baseInputFieldStyle + "#FCE300;");
                        break;
                    
                    case "scaled and icy":
                        nedText.setStyle(baseTextAreaStyle + "#8bc4cc;");
                        nedFace.setStyle(baseNedFaceStyle + "#8bc4cc;");
                        inputField.setStyle(baseInputFieldStyle + "#8bc4cc;");
                        break;
                    
                    case "blurryface":
                        nedText.setStyle(baseTextAreaStyle + "#F05443;");
                        nedFace.setStyle(baseNedFaceStyle + "#F05443;");
                        inputField.setStyle(baseInputFieldStyle + "#F05443;");
                        break;

                    case "vessel":
                        nedText.setStyle(baseTextAreaStyle + "#d5d5d5;");
                        nedFace.setStyle(baseNedFaceStyle + "#d5d5d5;");
                        inputField.setStyle(baseInputFieldStyle + "#d5d5d5;");
                        break;
                        
                    case "self titled":
                        nedText.setStyle(baseTextAreaStyle + "#9ccb3d;");
                        nedFace.setStyle(baseNedFaceStyle + "#9ccb3d;");
                        inputField.setStyle(baseInputFieldStyle + "#9ccb3d;");
                        break;


                    default:
                        nedText.appendText("Unknown style. Try: breach, clancy, scaled and icy, trench, blurryface, vessel, self titled");
                        break;
                }


               
            }else if (command.toLowerCase().startsWith("lyrics ")) {
                String songName = command.substring(7).trim();

                nedText.appendText("Ned is digging for the lyrics of " + songName + "...\n");

                fetchLyrics(songName, nedText);

            }else if (command.equalsIgnoreCase("ned vibe check")){
                
                int randomIndex = random.nextInt(nedQuotes.length);
                String chosenQuote = nedQuotes[randomIndex];
                
                nedText.appendText(chosenQuote);
            


            }
            
            
            
            else {
                nedText.appendText("Ned is confused. Unknown command: " + command);
            }
        
            inputField.clear();
            
        });
    }

   

}


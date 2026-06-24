package nedterminal;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




public class App extends Application{
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage ){


Label nedText = new Label("Welcome back. Chlorine levels stable.");

        // 2. Das Layout (StackPane legt alles einfach in die Mitte übereinander)
        StackPane root = new StackPane();
        root.getChildren().add(nedText);

        // 3. Die Szene (Der "Inhalt" des Fensters) mit Breite und Höhe (600x400 Pixel)
        Scene scene = new Scene(root, 600, 400);

        // 4. Das Fenster (Stage) einrichten und anzeigen
        primaryStage.setTitle("Ned Terminal"); // Der Name oben in der Fensterleiste
        primaryStage.setScene(scene);
        primaryStage.show(); // Vorhang auf!
    }

   

        
}


/* Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome back. Chlorine levels stable. What are we doing today?");
        

        while (isRunning){
            System.out.print("> ");
            String command = scanner.nextLine();

            
            if (command.equalsIgnoreCase("hello") || command.equalsIgnoreCase("hi")){
                System.out.println("Ned: Hi there, nice to see you my friend");
            }else if
                (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")){
                    System.out.println("Ned is going to sleep. Stay alive!");
                    isRunning = false;
            } else {
                System.out.println("Ned is confused. Unknown command " + command);
            }

        }
         scanner.close(); */
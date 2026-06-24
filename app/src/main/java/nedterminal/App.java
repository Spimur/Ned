package nedterminal;

import java.util.Scanner;




public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome back. Chlorine levels stable. What are we doing today?");
        

        while (isRunning){
            System.out.print("> ");
            String command = scanner.nextLine();

            
            if (command.equalsIgnoreCase("hello")){
                System.out.println("Ned: Hi there, nice to see you my friend");
            }else if
                (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")){
                    System.out.println("Ned is going to sleep. Stay alive!");
                    isRunning = false;
            } else {
                System.out.println("Ned is confused. Unknown command " + command);
            }

        }
         scanner.close();
    }

        
}


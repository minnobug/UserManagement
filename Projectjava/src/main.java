
import java.util.Scanner;
public class Main {
    public static void Main (String[] args) {
            Function User = new Function();
            Scanner scanner = new Scanner(System.in);
            String choice = "-1";     
                User.readFromFile();
                while(!choice.equals("0")) {
                System.out.println("User Management Program");
                System.out.println("1. Create user account");
                System.out.println("2. Check existing user");
                System.out.println("3. Search user information by user name");
                System.out.println("4. Delete user.");
                System.out.println("5. Dislay all user. ");
                System.out.println("0. Quit");
                System.out.print("Choose an option: ");
                choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        User.createUser();
                        User.writeToFile();
                        break;
                    case "2":
                        User.checkExistingUser();
                        break;
                    case "3":
                        User.searchUserByName();
                        break;
                    case "4":
                        User.deleteUserInformation();
                        User.writeToFile();
                        break;              
                    case"5":
                        User.displayUserInformation();
                        break;
                    case"0":
                        System.out.println("Exited.");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            
            }
        
            scanner.close();
        }
        // Define the User class to hold user information
}
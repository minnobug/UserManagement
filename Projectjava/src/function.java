
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class Function {
    ArrayList<User> database = new ArrayList();
    
     public  void createUser() {
       User User = new User();
       Scanner scanner = new Scanner(System.in);
        System.out.println("Create User Account");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        while (!isValidUsername(username)) {
            System.out.print("Username must be at least five characters and contain no spaces. Enter username again: ");
            username = scanner.nextLine();
        }
        User.setUsername(username);
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        User.setFirstName(firstName);
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        User.setLastName(lastName);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        while (!isValidPassword(password)) {
            System.out.print("Password must be at least six characters and contain no spaces. Enter password again: ");
            password = scanner.nextLine();
        }
        User.setPassword(password);
       
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        while (!isValidPhoneNumber(phone)) {
            System.out.print("Phone number must contain 10 digits. Enter phone number again: ");
            phone = scanner.nextLine();
        }
        User.setPhone(phone);
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!isValidEmail(email)) {
            System.out.print("Invalid email format. Enter email again: ");
            email = scanner.nextLine();
        }
         User.setEmail(email);
        database.add(User);
        System.out.println("User created successfully.");

    }
      private static boolean isValidUsername(String username) {
        return username.length() >= 5 && !username.contains(" ");
    }
      
       private static boolean isValidPassword(String password) {
        return password.length() >= 6 && !password.contains(" ");
    }

    private static boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    private static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    
  public void checkExistingUser() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter User Name to check: ");
    String userName = sc.nextLine(); 
    boolean userExists = false;

    for (User user : database) {
        if (userName.equals(user.getUsername())) {
            System.out.println("This user is already existed.");
            userExists = true;
            break;
        }
    }

    if (!userExists) {
        System.out.println("This user does not exist.");
    }
}

    public void searchUserByName() {
        boolean check = false;
        User User = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search User Information by user Name");
        System.out.print("Enter user to search: ");
        String userName = scanner.nextLine();
        for(User user: database){
        if(userName.equals(user.getUsername())){
         dislayOneUser(User);
         check = true;
         break;
        }
        }   
         if(check == false) {System.out.println("User not found.");}
        }
public void dislayOneUser(User User){
    System.out.println("User name:"+ User.getUsername());
    System.out.println("User first name:"+ User.getFirstName());
    System.out.println("User last name:"+ User.getLastName());
    System.out.println("User name:"+ User.getPassword());
    System.out.println("User name:"+ User.getPhone());  
}

 public  void deleteUserInformation() {
        Scanner sc = new Scanner(System.in);
    System.out.print("Enter user name to delete: ");
    String userName = sc.nextLine();
    boolean userFound = false;

    // Use an iterator to safely remove an item from the list during iteration
    Iterator<User> iterator = database.iterator();
    while (iterator.hasNext()) {
        User user = iterator.next();
        if (userName.equals(user.getUsername())) {
            iterator.remove();
            System.out.println("User " + userName + " has been removed.");
            userFound = true;
            break;
        }
    }
    if (!userFound) {
        System.out.println("User not found.");
    }
    
 }
 
  public void displayUserInformation() {
       boolean check = false;
           User User =new User();
        for (User user : database) {
            dislayOneUser(user);
            check = true;} 
        if(check == false){System.out.println("Can found any users.");}
    }
   public void readFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader("user.dat"))) {
        String line;
        database.clear();
        while ((line = reader.readLine()) != null) {
            String[] userDetails = line.split(",");
            if (userDetails.length == 6) {
                User user = new User();
                user.setUsername(userDetails[0]);
                user.setFirstName(userDetails[1]);
                user.setLastName(userDetails[2]);
                user.setPassword(userDetails[3]);
                user.setPhone(userDetails[4]);
                user.setEmail(userDetails[5]);
                database.add(user);
            }
        }
        System.out.println("User data loaded from file.");
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("Error reading from file: " + e.getMessage());
    }
}

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.dat"))) {
            for (User user : database) {
                writer.write(user.getUsername() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getPassword() + "," + user.getPhone() + "," + user.getEmail());
                writer.newLine();
            }
            System.out.println("User data saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}


 
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.io.IOException;
import java.io.Serializable;


public class UserManagement {
    private static final String FILE_NAME = "user.dat";
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        printAllUsersFromFile(userList);
        

        boolean exit = false;
        while (!exit) {
            System.out.println("User Management System");
            System.out.println("1. Create a user account");
            System.out.println("2. Check user existence");
            System.out.println("3. Search user information by name");
            System.out.println("4. Update users");
            System.out.println("5. Save the account to file");
            System.out.println("6. Print user list from file");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: "); 

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    createUser();
                    break;
                case 2:
                    checkUserExistence(userList);
                    break;
                case 3:
                    searchUserByName(userList);
                    break;
                case 4:
                    updateUser(userList);
                    break;
                case 5:
                    saveUsersToFile(userList);
                    break;
                case 6:
                    printAllUsersFromFile(userList);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    
    
    // Assuming you have a User class

    private static void updateUser(List<User> userList) {
        System.out.println("Update User");
        // Implement update logic
    }



    private static void saveUsersToFile(List<User> userList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(userList);
            System.out.println("Users saved to file.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving users to file: " + e.getMessage());
        }

        // Ask to go back to the main menu
        System.out.println("Press Enter to go back to the main menu...");
        scanner.nextLine(); // Wait for Enter key press
    }


        
    private static void createUser() {
        System.out.println("Create User Account:");
        System.out.print("Enter username (at least 5 characters, no spaces): ");
        String username = scanner.nextLine().trim();
        if (!isValidUsername(username)) {
            System.out.println("Invalid username format!");
            createUser();
            return;
        }

        // Check if username is unique (not implemented in this example)

        System.out.print("Enter last name: ");
        @SuppressWarnings("unused")
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter first name: ");
        @SuppressWarnings("unused")
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter password (at least 6 characters, no spaces): ");
        String password = scanner.nextLine().trim();
        if (!isValidPassword(password)) {
            System.out.println("Invalid password format!");
            createUser();
            return;
        }

        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine().trim();
        if (!confirmPassword.equals(password)) {
            System.out.println("Password confirmation does not match!");
            createUser();
            return;
        }

        System.out.print("Enter phone number (10 digits): ");
        String phoneNumber = scanner.nextLine().trim();
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number format!");
            createUser();
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format!");
            createUser();
            return;
        }

        // All data is valid, proceed with creating the user account
        System.out.println("User account created successfully!");
        // Perform account creation (not implemented in this example)
    }

    private static boolean isValidUsername(String username) {
        return username.length() >= 5 && !username.contains(" ");
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 6 && !password.contains(" ");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    /**
     * @param userList
     */
    private static void checkUserExistence(List<User> userList) {
    System.out.println("Check Existing User");
    System.out.print("Enter username to check: ");
    String usernameToCheck = scanner.nextLine().trim();

    boolean userExists = false;
    for (User user : userList) {
        if (user.getUsername().equals(usernameToCheck)) {
            userExists = true;
            break;
        }
    }

    if (userExists) {
        System.out.println("Exist User");
    } else {
        System.out.println("No User Found!");
    }

    // Ask to go back to the main menu
    System.out.println("Press Enter to go back to the main menu...");
    scanner.nextLine(); // Wait for Enter key press
    }



    private static void searchUserByName(List<User> userList) {
    System.out.println("Search User Information by Name");
    System.out.print("Enter search string (part of first name or last name): ");
    String searchString = scanner.nextLine().trim();

    List<User> matchingUsers = new ArrayList<>();

    for (User user : userList) {
        if (((String) user.getUsername()).toLowerCase().contains(searchString.toLowerCase()) ||
            user.getLastName().toLowerCase().contains(searchString.toLowerCase())) {
            matchingUsers.add(user);
        }
    }

    if (matchingUsers.isEmpty()) {
        System.out.println("No user found.");
    } else {
        // Sort matchingUsers by first name
        Collections.sort(matchingUsers, Comparator.comparing(t -> t.getLastName()));

        System.out.println("Matching users:");
        for (User user : matchingUsers) {
            System.out.println(user);
        }
    }

    // Ask to go back to the main menu
    System.out.println("Press Enter to go back to the main menu...");
    scanner.nextLine(); // Wait for Enter key press
}


    @SuppressWarnings("unused")
    private static void updateUserInformation(List<User> userList) {
    System.out.println("Update User Information");
    System.out.print("Enter username: ");
    String username = scanner.nextLine().trim();

    // Find the user
    User userToUpdate = null;
    for (User user : userList) {
        if (user.getUsername().equals(username)) {
            userToUpdate = user;
            break;
        }
    }

    if (userToUpdate == null) {
        System.out.println("Username does not exist.");
        return;
    }

    // Update user information
    System.out.println("Enter new information (leave blank to keep old information):");
    System.out.print("Enter last name: ");
    String newLastName = scanner.nextLine().trim();
    if (!newLastName.isEmpty()) {
        userToUpdate.setLastName(newLastName);
    }

    System.out.print("Enter first name: ");
    String newFirstName = scanner.nextLine().trim();
    if (!newFirstName.isEmpty()) {
        userToUpdate.setFirstName(newFirstName);
    }

    System.out.print("Enter password: ");
    String newPassword = scanner.nextLine().trim();
    if (!newPassword.isEmpty()) {
        userToUpdate.setPassword(newPassword);
    }

    System.out.print("Enter phone number: ");
    String newPhoneNumber = scanner.nextLine().trim();
    if (!newPhoneNumber.isEmpty()) {
        userToUpdate.setPhoneNumber(newPhoneNumber);
    }

    System.out.print("Enter email: ");
    String newEmail = scanner.nextLine().trim();
    if (!newEmail.isEmpty()) {
        userToUpdate.setEmail(newEmail);
    }

    System.out.println("User information updated successfully.");
    // Ask to go back to the main menu
    System.out.println("Press Enter to go back to the main menu...");
    scanner.nextLine(); // Wait for Enter key press
   }
    
   
    @SuppressWarnings("unused")
    private static void deleteUserInformation(List<User> userList) {
    System.out.println("Delete User Information");
    System.out.print("Enter username: ");
    String username = scanner.nextLine().trim();

    // Find the user
    User userToDelete = null;
    for (User user : userList) {
        if (user.getUsername().equals(username)) {
            userToDelete = user;
            break;
        }
    }

    if (userToDelete == null) {
        System.out.println("Username does not exist.");
        return;
    }

    // Remove the user from the list
    userList.remove(userToDelete);
    System.out.println("User information deleted successfully.");
    // Ask to go back to the main menu
    System.out.println("Press Enter to go back to the main menu...");
    scanner.nextLine(); // Wait for Enter key press
}

    private static void printAllUsersFromFile(List<User> userList2) {
    List<User> userList = new ArrayList<>();

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
        Object obj = ois.readObject();
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            for (Object o : list) {
                if (o instanceof User) {
                    userList.add((User) o);
                } else {
                    System.out.println("Invalid object found in file: " + o.getClass().getName());
                }
            }
            System.out.println("Users loaded from file.");
        } else {
            System.out.println("Invalid data format in file.");
            return;
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error occurred while loading users from file.");
        return;
    }

    if (userList.isEmpty()) {
        System.out.println("No users found in the file.");
        return;
    }

    // Sort userList by first name

    System.out.println("User list from file:");
    for (User user : userList) {
        System.out.println(user);
    }

    // Ask to go back to the main menu
    System.out.println("Press Enter to go back to the main menu...");
    scanner.nextLine(); // Wait for Enter key press
    }

}
    
    class User implements Serializable {
    private String username;
    private String password; // You might want to encrypt this
    private String firstName;
    private String lastName;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void setEmail(String newEmail) {
        // Implementation for setEmail
    }

    public void setPhoneNumber(String newPhoneNumber) {
        // Implementation for setPhoneNumber
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public void setPassword(String newPassword) {
        // Implementation for setPassword
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public String toString() {
        return "Username: " + username + ", Password: " + password + ", First Name: " + firstName + ", Last Name: " + lastName;
    }
}


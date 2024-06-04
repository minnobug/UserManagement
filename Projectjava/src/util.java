import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;


public class util {
    private static List<User> database= new ArrayList<>();

    public static void createUser() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create User Account");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        while (!isValidUsername(username)) {
            System.out.print("Username must be at least five characters and contain no spaces. Enter username again: ");
            username = scanner.nextLine();
        }
        while (isDuplicateUsername(username)) {
            System.out.print("Username already exists. Enter a different username: ");
            username = scanner.nextLine();
        }

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        while (!isValidPassword(password)) {
            System.out.print("Password must be at least six characters and contain no spaces. Enter password again: ");
            password = scanner.nextLine();
        }

        System.out.print("Confirm password: ");
        String confirm = scanner.nextLine();
        while (!confirm.equals(password)) {
            System.out.print("Passwords do not match. Enter password again: ");
            confirm = scanner.nextLine();
        }

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        while (!isValidPhoneNumber(phone)) {
            System.out.print("Phone number must contain 10 digits. Enter phone number again: ");
            phone = scanner.nextLine();
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!isValidEmail(email)) {
            System.out.print("Invalid email format. Enter email again: ");
            email = scanner.nextLine();
        }

        User newUser = new User(username, firstName, lastName, password, phone, email);
        database.add(newUser);
        System.out.println("User created successfully.");

        System.out.println("Do you want to go back to the main menu? (Y/N)");
        String choice = scanner.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            // Call method to go back to main menu
        }
    }

    private static boolean isValidUsername(String username) {
        return username.length() >= 5 && !username.contains(" ");
    }

    private static boolean isDuplicateUsername(String username) {
        for (User user : database) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
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


    public static void checkExistingUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Check Existing User");

            System.out.print("Enter username to check: ");
            String usernameToCheck = scanner.nextLine();

            boolean userExists = isUserExists(usernameToCheck);

            if (userExists) {
                System.out.println("Exist User");
            } else {
                System.out.println("No User Found!");
            }

            System.out.println("Do you want to go back to the main menu? (Y/N)");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("Y")) {
                // Call method to go back to main menu
            }
        }
    }
        private static boolean isUserExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String existingUsername = userData[0];
                if (existingUsername.equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
        

        public static void searchUserByName() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search User Information by Name");

        System.out.print("Enter search string (part of first name or last name): ");
        String searchString = scanner.nextLine();

        List<User> matchingUsers = searchUsers(searchString);

        if (matchingUsers == null || matchingUsers.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Found users:");
            for (User user : matchingUsers) {
                System.out.println(user);
            }
        }

        System.out.println("Do you want to go back to the main menu? (Y/N)");
        String choice = scanner.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            // Call method to go back to main menu
        }
    }

    private static List<User> searchUsers(String searchString) {
        List<User> matchingUsers = new ArrayList<>();
        for (User user : database) {
            if (user.getFirstName().contains(searchString) || user.getLastName().contains(searchString)) {
                matchingUsers.add(user);
            }
        }
        return matchingUsers.isEmpty() ? null : matchingUsers;
    }
    
    public static void updateUserInformation() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Update User Information");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            User userToUpdate = getUserByUsername(username);
            if (userToUpdate == null) {
                System.out.println("Username does not exist.");
                return;
            }

            // Ask for new information
            System.out.println("Enter new information (leave blank to keep old information):");
            System.out.print("First name (Current: " + userToUpdate.getFirstName() + "): ");
            String newFirstName = scanner.nextLine().trim();
            if (!newFirstName.isEmpty()) {
                userToUpdate.setFirstName(newFirstName);
            }

            System.out.print("Last name (Current: " + userToUpdate.getLastName() + "): ");
            String newLastName = scanner.nextLine().trim();
            if (!newLastName.isEmpty()) {
                userToUpdate.setLastName(newLastName);
            }

            System.out.print("Password (Current: " + userToUpdate.getPassword() + "): ");
            String newPassword = scanner.nextLine().trim();
            if (!newPassword.isEmpty()) {
                userToUpdate.setPassword(newPassword);
            }

            System.out.print("Phone (Current: " + userToUpdate.getPhone() + "): ");
            String newPhone = scanner.nextLine().trim();
            if (!newPhone.isEmpty()) {
                userToUpdate.setPhoneNumber(newPhone);
            }

            System.out.print("Email (Current: " + userToUpdate.getEmail() + "): ");
            String newEmail = scanner.nextLine().trim();
            if (!newEmail.isEmpty()) {
                userToUpdate.setEmail(newEmail);
            }

            System.out.println("User information updated successfully.");

            System.out.println("Do you want to go back to the main menu? (Y/N)");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("Y")) {
                // Call method to go back to main menu
            }
        }
    }

    public static void deleteUserInformation() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Delete User Information");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            User userToDelete = getUserByUsername(username);
            if (userToDelete == null) {
                System.out.println("Username does not exist.");
                return;
            }

            // Perform deletion
            database.remove(userToDelete);
            System.out.println("User information deleted successfully.");

            System.out.println("Do you want to go back to the main menu? (Y/N)");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("Y")) {
                // Call method to go back to main menu
            }
        }
    }

    private static User getUserByUsername(String username) {
        for (User user : database) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    

    public static void saveToFile(User[] users) {
        try {
            FileWriter writer = new FileWriter("user.dat");
            for (User user : users) {
                writer.write(user.toString() + "\n");
            }
            writer.close();
            System.out.println("User information saved to user.dat");
        } catch (IOException e) {
            System.err.println("Error saving user information: " + e.getMessage());
        }
    }

    public static List<User> loadUsersFromFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("user.dat"))) {
            while ((reader.readLine()) != null) {
                // Parse the line and create User objects
                // Add them to the users list
            }
        } catch (IOException e) {
            System.err.println("Error loading user information: " + e.getMessage());
        }
        return users;
    }

    public static void displayUsers(List<User> users) {
        Collections.sort(users, Comparator.comparing(User::getFirstName));
        for (User user : users) {
            System.out.println(user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        }
    }
    public static void main(String[] args) {
        List<User> users = loadUsersFromFile();
        displayUsers(users);

        // Ask if the user wants to go back to the main menu
        // Handle user input accordingly
    }
}

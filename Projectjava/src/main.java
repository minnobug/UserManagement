import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class main {
    private static final String FILE_NAME = "user.dat";

    private static List<User> loadUsersFromFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            while ((br.readLine()) != null) {
                // Parse each line to extract user information and create User objects
                // Add User objects to the users list
            }
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
        return users;
    }

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
            if (((String) user.getUsername()).contains(searchString) || user.getLastName().contains(searchString)) {
                matchingUsers.add(user);
            }
        }
        return matchingUsers.isEmpty() ? null : matchingUsers;
    }

    public static void updateUser() {
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
            System.out.print("First name (Current: " + userToUpdate.getLastName() + "): ");
            String newFirstName = scanner.nextLine().trim();
            if (!newFirstName.isEmpty()) {
                userToUpdate.setFirstName(newFirstName);
            }

            System.out.print("Last name (Current: " + userToUpdate.getLastName() + "): ");
            String newLastName = scanner.nextLine().trim();
            if (!newLastName.isEmpty()) {
                userToUpdate.setFirstName(newLastName);
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

    private static main.User getUserByUsername(String username) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getUserByUsername'");
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
   

    public static void saveToFile(List<main.User> users) {
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


    static class User {

        public User(String username, String firstName, String lastName, String password, String phone, String email) {
            //TODO Auto-generated constructor stub
        }

        public void setEmail(String newEmail) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
        }

        public String getEmail() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
        }

        public void setPhoneNumber(String newPhone) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setPhoneNumber'");
        }

        public String getPhone() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getPhone'");
        }

        public void setPassword(String newPassword) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
        }

        public String getPassword() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
        }

        public void setFirstName(String newFirstName) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setFirstName'");
        }

        public String getLastName() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getLastName'");
        }

        public Object getUsername() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<main.User> users = main.loadUsersFromFile();
        
            boolean running = true;
            while (running) {
                System.out.println("User Management Program");
                System.out.println("1. Create user account");
                System.out.println("2. Check existing user");
                System.out.println("3. Search user information by name");
                System.out.println("4. Update user");
                System.out.println("5. Save account to file");
                System.out.println("6. Quit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
        
                switch (choice) {
                    case 1:
                        main.createUser();
                        break;
                    case 2:
                        main.checkExistingUser();
                        break;
                    case 3:
                        main.searchUserByName();
                        break;
                    case 4:
                        main.updateUser(users, scanner);
                        break;
                    case 5:
                        main.saveToFile(users);
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        
            scanner.close();
        }
        // Define the User class to hold user information
    }

    @Override
    public String toString() {
        return "main []";
    }
    public static void updateUser(List<main.User> users, Scanner scanner) {
        
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    public static String getFileName() {
        return FILE_NAME;
    }
}


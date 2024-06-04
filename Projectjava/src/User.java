import java.io.Serializable;

class User implements Serializable {
    private String username;
    private String password; // You might want to encrypt this
    private String firstName;
    private String lastName;
    public User(String username, String firstName, String lastName, String password, String phone, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
    


    public User() {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }



    public String getPhone() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getPhone'");
    }



    public String getEmail() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }
}
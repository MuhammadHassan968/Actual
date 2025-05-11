package backend;

// import models.*;         // Import all classes from models package (Hasan's custom classes)
import java.io.FileWriter;       // For writing to files
import java.io.IOException;      // For handling IO exceptions
import java.io.BufferedWriter;   // For efficient file writing
import java.io.File;             // For file operations
import java.io.FileNotFoundException;  // For handling file not found exceptions
import java.util.Scanner;        // For user input

// Class to handle user authentication
class Authenticator {
    SceneController switchScene = new SceneController();
    // Arrays to store usernames and passwords (max 50 users)
    String[] userName = new String[50];
    int[] password = new int[50];

    // Counters and indices for user management
    int userCount = 5;   // Current number of users (initialized with 5 default users)
    int nameIndex = 8;   // Temporary index for userName verification (initialized to invalid value)
    int passwordIndex = -1;  // Temporary index for password verification (initialized to invalid value)

    // Constructor to initialize with default users
    public Authenticator (String[] names, int[] key) {
        // Populate userName and password arrays with default values
        for (int i = 0; i < 5; i++) {
            userName[i] = names[i];
            password[i] = key[i];
        }
    }

    // Method to register new users
    public void signUp (String newUserName, int newPassword) {
        // Store new user in arrays
        userName[userCount] = newUserName;
        password[userCount] = newPassword;

        // Write new user to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(newUserName + "," + newPassword);  // Save as CSV format
            writer.newLine();  // New line for next user
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
        userCount++;  // Increment user count
        System.out.println("Signup successful! Now please login.");
    }

    // Method to load users from file
    public void loadUsersFromFile() {
        try {
            File file = new File("users.txt");  // Open users file
            Scanner fileScanner = new Scanner(file);  // Scanner to read file

            int i = 0;
            // Read all lines in file (max 50 users)
            while (fileScanner.hasNextLine() && i < 50) {
                String line = fileScanner.nextLine();
                String[] userData = line.split(",");  // Split CSV line
                userName[i] = userData[0];  // Store userName
                password[i] = Integer.parseInt(userData[1]);  // Convert and store password
                i++;
            }
            userCount = i;  // Update total user count

        } catch (FileNotFoundException e) {
            System.out.println("User file not found, starting fresh.");
        }
    }

    // Method to verify userName exists
    public void verifyUserName(String name){
        for(int i = 0; i< userCount; i++){
            if(userName[i].equals(name)){
                nameIndex =i;  // Store index if userName found
            }
        }
        if(nameIndex ==8){  // If userName not found (8 is invalid index)
            System.out.println("Invalid userName ! not found in record");
        }
    }

    // Method to verify password exists
    public void verifypassword(int key){
        for(int i = 0; i< userCount; i++){
            if(password[i]==key){
                passwordIndex =i;  // Store index if password found
            }
        }
        if(passwordIndex ==-1) {  // If password not found (-1 is invalid index)
            System.out.println("invalid password!! Not found in record");
        }
    }

    // Method to verify userName-password match
    public boolean verify(){
        if (passwordIndex == nameIndex) {  // If both indices match
            switchScene.switchToScene3();
            return true;
        }
        else {
            System.out.println("your userName don't match your password ");
            return false;
        }
    }
}
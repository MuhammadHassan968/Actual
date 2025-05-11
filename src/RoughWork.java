// Import required packages and classes
import models.*;         // Import all classes from models package (Hasan's custom classes)
import java.io.FileWriter;       // For writing to files
import java.io.IOException;      // For handling IO exceptions
import java.io.BufferedWriter;   // For efficient file writing
import java.io.File;             // For file operations
import java.io.FileNotFoundException;  // For handling file not found exceptions
import java.util.Scanner;        // For user input

// Class to handle user authentication
class Authenticator {
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
        if(passwordIndex == nameIndex){  // If both indices match
//            System.out.println("Welcome 😘 NOW LETS GET STARTED GIVE ME SOME FOLLOWING CREDENTIALS ");
            return true;
        }else{
            System.out.println("your userName donot match your password ");
            return false;
        }
    }
}

// backend.Main class containing program entry point
public class RoughWork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create scanner for user input

        // Display welcome message
//        System.out.println();
//        System.out.println();
//        System.out.println("              ********* WELCOME TO THE FITNESS APP ******** ");
//        System.out.println();
//        System.out.println("                              HOPE YOU ARE DOING WELL \n");
//        System.out.println();
//        System.out.println("                                      FOR LOGIN");
//        System.out.println("**********************PLEASE ENTER THE REQUIRED CREDENTIALS **********************");
//        System.out.println();

        boolean authenticated=false;  // Authentication flag
        // Default user credentials
        String [] names1={"hassan123@gmail.com","fozan456@gmail.com","abdullah789@gmail.com","syed987@gmail.com","ali654@gmail.com"};
        int [] key1={123456789,456789123,789123456,987654321,654321987};

        // Create authenticator object with default users
        Authenticator obj1=new Authenticator(names1, key1);

        // backend.Main authentication loop
        while(!authenticated) {
            // Display login/signup options
            System.out.println("Press 1 to Login");
            System.out.println("Press 2 to Signup");
            int choice = sc.nextInt();

            // Handle signup
            if (choice == 2) {
                System.out.println("Enter new userName (email): ");
                String newUsername = sc.next();
                System.out.println("Enter new password (numbers only): ");
                int newPassword = sc.nextInt();
                obj1.signUp(newUsername, newPassword);
            }

            // Get credentials
            System.out.println("ENTER YOUR USERNAME:");
            String username = sc.next();
            System.out.println();
            System.out.println("ENTER YOUR PASSWORD:");
            int password = sc.nextInt();

            // Verify credentials
            obj1.loadUsersFromFile();
            obj1.verifyUserName(username);
            obj1.verifypassword(password);

            System.out.println();
            System.out.println();

            // If authentication successful
            if (obj1.verify() == true) {
                authenticated = true;

                // Start fitness features
                waterreminder remind = new waterreminder();
                remind.reminder();
                System.out.println();
                System.out.println("***********************************************************************");
                System.out.println();

                // Get user habits (weight/height)
                habit obj = new habit();
                obj.display2();

                // Calculate and display BMI
                calculation obj2 = new calculation();
                obj2.bmical(obj.weigth, obj.heigth);
                obj2.index();
                obj2.display2();

                System.out.println("**************************************************************************");

                // Track calories
                calorietracker obj3 = new calorietracker();
                obj3.settingcalorie();
                obj3.takeinput();
                System.out.println("Total calories you have taken are : " + obj3.getTotalcalories());

                dietplan diet = new dietplan();

                // Workout plan option
                System.out.println("\nWould you like to enter your workout plan and check your muscle rank? (1 - Yes / 2 - No)");
                int workoutChoice = sc.nextInt();
                sc.nextLine();

                if (workoutChoice == 1) {
                    workoutplan workout = new workoutplan();
                    workout.inputWorkout();
                    workout.analyzeWorkout();
                    System.out.println("\nWould you like to check your rank for each exercise? (1 - Yes / 2 - No)");
                    int rankChoice = sc.nextInt();
                    sc.nextLine();

                    if (rankChoice == 1) {
                        workout.showRank();
                    } else {
                        System.out.println("No problem! Let's continue...");
                    }
                }
                else {
                    System.out.println("Skipping workout plan. Stay fit! 🏃‍♂️");
                }

                // Diet plan option
                System.out.print("\nDo you already have a diet plan? (1 - Yes / 2 - No): ");
                int dietChoice = sc.nextInt();
                sc.nextLine();

                if (dietChoice == 1) {
                    diet.suggestPlanBasedOnGoalAndBMI(obj2.index());
                } else {
                    System.out.println("We will help you create a diet plan!");
                    diet.suggestPlanBasedOnGoalAndBMI(obj2.index());
                }
            } else {
                System.out.println("kindly try again ");
            }
        }
    }
}

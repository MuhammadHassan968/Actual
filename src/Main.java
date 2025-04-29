import models.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

class authenticator{
    String [] username=new String[50];
    int[] password=new int[50];
    int usercount=5;
    int nameindex=8;
    int passwordindex=-1;


    public authenticator(String[] names,int[] key){
        for(int i=0;i<5;i++){
            username[i]=names[i];
            password[i]=key[i];
        }
    }
    public void signup(String newUsername, int newPassword) {
        username[usercount] = newUsername;
        password[usercount] = newPassword;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(newUsername + "," + newPassword);  // Save username and password in a single line
            writer.newLine();  // Move to the next line for the next user
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
        usercount++;
        System.out.println("Signup successful! Now please login.");
    }
    public void loadUsersFromFile() {
        try {
            File file = new File("users.txt");
            Scanner fileScanner = new Scanner(file);

            int i = 0;
            while (fileScanner.hasNextLine() && i < 50) {
                String line = fileScanner.nextLine();
                String[] userData = line.split(",");  // Split the username and password by comma
                username[i] = userData[0];
                password[i] = Integer.parseInt(userData[1]);  // Convert password to int
                i++;
            }
            usercount = i;  // Update user count based on how many users were loaded

        } catch (FileNotFoundException e) {
            System.out.println("User file not found, starting fresh.");
        }
    }

    public void verifyusername(String name){
        for(int i=0;i<usercount;i++){
            if(username[i].equals(name)){
                nameindex=i;
            }
        }
        if(nameindex==8){
            System.out.println("Invalid username ! not found in record");
        }
    }
    public void verifypassword(int key){
        for(int i=0;i<usercount;i++){
            if(password[i]==key){
                passwordindex=i;
            }
        }
        if(passwordindex==-1) {
            System.out.println("invalid password!! Not found in record");
        }

    }
    public boolean verify(){
        if(passwordindex==nameindex){
            System.out.println("Welcome 😘 NOW LETS GET STARTED GIVE ME SOME FOLLOWING CREDENTIALS ");
            return true;

        }else{
            System.out.println("your username donot match your password ");
            return false;
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println("              ********* WELCOME TO THE FITNESS APP ******** ");
        System.out.println();
        System.out.println("                              HOPE YOU ARE DOING WELL \n");
        System.out.println();
        System.out.println("                                      FOR LOGIN");
        System.out.println("**********************PLEASE ENTER THE REQUIRED CREDENTIALS **********************");
        System.out.println();
        boolean authenticated=false;
        String [] names1={"hassan123@gmail.com","fozan456@gmail.com","abdullah789@gmail.com","syed987@gmail.com","ali654@gmail.com"};
        int [] key1={123456789,456789123,789123456,987654321,654321987};


        authenticator obj1=new authenticator(names1, key1);
        while(!authenticated) {
            System.out.println("Press 1 to Login");
            System.out.println("Press 2 to Signup");
            int choice = sc.nextInt();

            if (choice == 2) {
                System.out.println("Enter new username (email): ");
                String newUsername = sc.next();
                System.out.println("Enter new password (numbers only): ");
                int newPassword = sc.nextInt();
                obj1.signup(newUsername, newPassword);
            }
            System.out.println("ENTER YOUR USERNAME:");
            String username = sc.next();
            System.out.println();
            System.out.println("ENTER YOUR PASSWORD:");
            int password = sc.nextInt();
            obj1.loadUsersFromFile();
            obj1.verifyusername(username);
            obj1.verifypassword(password);



            System.out.println();
            System.out.println();
            if (obj1.verify() == true) {
                authenticated = true;
                waterreminder remind = new waterreminder();
                remind.reminder();
                System.out.println();
                System.out.println("***********************************************************************");
                System.out.println();

                habit obj = new habit();
                obj.display2();

                calculation obj2 = new calculation();
                obj2.bmical(obj.weigth, obj.heigth);
                obj2.index();
                obj2.display2();

                System.out.println("**************************************************************************");

                calorietracker obj3 = new calorietracker();
                obj3.settingcalorie();
                obj3.takeinput();
                System.out.println("Total calories you have taken are : " + obj3.getTotalcalories());

                dietplan diet = new dietplan();

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

                System.out.print("\nDo you already have a diet plan? (1 - Yes / 2 - No): ");
                int dietChoice = sc.nextInt();
                sc.nextLine();

                if (dietChoice == 1) {

                diet.suggestPlanBasedOnGoalAndBMI(obj2.index());
                } else {
                    System.out.println("We will help you create a diet plan!");
                    diet.suggestPlanBasedOnGoalAndBMI(obj2.index());

                }
            }else {
                    System.out.println("kindly try again ");
                }
            }
        }


    }

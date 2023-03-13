/**
 * Animal Adoption System class
 * Instance Variables:
 * BFF helper, ArrayList</User> userDatabase, User currentUser, ArrayList</AnimalType> allAnimals
 * Description:
 * Class contains animal adoption menu with several choices for user action.
 * Class has main method for running the entire program.
 * @author Benjamin Granat
 * email: bgranat@usc.edu
 * ITP 265
 * Assignment 07
 */

import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AnimalAdoptionSystem {
    //Instance Variables
    private BFF helper;
    private ArrayList<User> userDatabase;
    private User currentUser;
    private ArrayList<AnimalType> allAnimals;
    //Initialization
    public AnimalAdoptionSystem(){
        helper = new BFF();
        userDatabase = new ArrayList<>();
        currentUser = null;
        allAnimals = Animal_FileReader.readEmojiAnimalFile();
    }
    // Run method
    // No parameters.
    public void run(){
        //Temp boolean
        boolean menu = true;
        while(menu){
            //Displays menu
            displayMenu();
            //Prompts user choice
            int user_input = helper.inputInt(">");
            //Transforms int into enum
            LoginMenu choice = LoginMenu.getOptionNumber(user_input);
            //Switch statement of LoginMenu choices
            switch(choice){
                case MAKE_FAKE_ACCOUNTS:
                    makeFakeAccounts();
                    break;
                case DISPLAY_USER_NAMES:
                    displayUserNames();
                    break;
                case CREATE_ACCOUNT:
                    createAccount();
                    break;
                case LOGIN:
                    login();
                    break;
                case LOGOUT:
                    logout();
                    break;
                case CHANGE_PASSWORD:
                    changePassWord();
                    break;
                case ADOPT_PET:
                    adoptPet();
                    break;
                case DISPLAY_USER:
                    displayUser();
                    break;
                case QUIT:
                    menu = false;
                    break;
            }
        }
    }

    // displayMenu method.
    // No parameters
    private void displayMenu() {
        //Uses printFancy to displayMenu
        helper.printFancy(LoginMenu.getMenuString());
    }

    // findUser method.
    // String parameter.
    // Finds User associated with string parameter.
    public User findUser(String name) {
        //Temp boolean, user, and int
        boolean match = false;
        User user = null;
        int i = 0;
        // While loop until match is found
        while (!match && i < userDatabase.size()) {
            // Temp User
            User database_user = userDatabase.get(i);
            // Checks if user equals name and switches temp boolean to true
            if (database_user.getUserName().equalsIgnoreCase(name)) {
                match = true;
                user = database_user;
                break;
            }
            i++;
        }
        return user;
    }

    // verifyUser method.
    // No parameter.
    // Checks if there is an active user (user is logged in).
    // Returns boolean associated with login status.
    private boolean verifyUser() {
        // Checks if currentUser is null
        if (currentUser == null) {
            System.out.println("Someone needs to be logged in in order to complete this action.");
            return false;
        }
        return true;
    }
    // makeFakeAccounts method.
    // No parameter.
    // Creates two accounts used for testing and error-checking.
    public void makeFakeAccounts(){
        userDatabase.add(new User("beng", "nowayin", LocalDate.of(2001,5, 1)));
        userDatabase.add(new User("beng", "nowayin", LocalDate.now()));
    }

    // displayUserNames method.
    // No parameter.
    // Displays all usernames stored in the userDatabase.
    private void displayUserNames() {
        for(User user: userDatabase){
            System.out.println(user.getUserName());
        }
    }

    // createAccount method.
    // No parameter.
    // Prompts user for input on account details.
    // Uses account details to create new user in userDatabase.
    private void createAccount() {
        // Initializes user prompt for username
        String username = helper.input("Enter a username: ");
        // While loop to continue while entered username is already in the database
        while (findUser(username) !=  null) {
            System.out.println("Username taken.");
            username = helper.input("Enter a username: ");
        }
        // bday, password, and confirm password inputs
        LocalDate bday = helper.inputDate("What is your birthday? ");
        String password = helper.input("Enter a password: ");
        String confirm = helper.input("Confirm password: ");
        //if password = confirm, create new user with input details
        if (password.equals(confirm)) {
            User new_user = new User(username, password, bday);
            userDatabase.add(new_user);
        }
        else{
            System.out.println("Password was not created due to passwords not matching");
        }
    }

    // login method.
    // No parameter.
    // Allows user to log into the system using account information.
    // Makes sure account exists in userDatabase.
    // Changes currentUser from null to user.
    public void login(){
        // Prompt username input
        String user_input = helper.input("Please enter a username: ");
        // Checks if user exists with input username
        for(User user: userDatabase){
            if(user_input.equalsIgnoreCase(user.getUserName())){
                // Prompts user to enter password
                String password = helper.input("Enter Password: ");
                // If user password matches password, user is now logged in
                //currentUser's null value is replaced by user
                if(user.checkPassword(password)){
                    currentUser = user;
                    System.out.println("User is logged in.");
                    return;
                }
                else {
                    System.out.println("Passwords did not match.");
                }
            }
        }
        System.out.println("User was not found.");
    }

    // logout method.
    // No parameter.
    // Changes currentUser back to null to indicate user logging out.
    public void logout(){
        currentUser = null;
    }

    // changePassWord method.
    // No parameter.
    // Allows user to change password for a current account in userDatabase.
    // Checks login boolean and prompts user for their current password first.
    // Then, method prompts user for new password.
    // Utilizes updatePassword method from User class to change password.
    private void changePassWord() {
        //Temp boolean
        boolean login_check = verifyUser();
        //Checks if user is logged in
        if(login_check == true){
            // Prompts user to enter current password
            String password = helper.input("Enter your current password: ");
            // While loop for incorrect password entry
            while(!currentUser.checkPassword(password)){
                System.out.println("Incorrect password.");
                password = helper.input("Enter your current password: ");
            }
            // Prompts user for new password
            String newPassword = helper.input("Enter new password: ");
            // Uses updatePassword method from User.java
            // Changes old password to new password
            currentUser.updatePassword(password, newPassword);
            System.out.println("Password has been updated successfully.");
        }
    }

    // adoptPet method.
    // No parameter.
    // Allows user to adopt a pet.
    // User enters category choice.
    // Then, user will choose either pet or exotic.
    // From there, they will see an aggregated list of animals, where they can choose using an int.
    // The adopted pet is added to their user profile.
    private void adoptPet() {
        // Checks that user is logged in
        if(currentUser == null){
            System.out.println("No user is currently logged in");
            return;
        }
        // Gets currentUser's number of adopted pets
        int pets_qty = currentUser.getNumPets();
        System.out.println("You currently have " + pets_qty + " pets.");
        // Checks if number of pets exceeds max pets = 5
        if(pets_qty >= User.DEFAULT_MAX_PETS){
            System.out.println("You are not allowed anymore pets");
            return;
        }
        // Prints all categories
        System.out.println("There are " + AnimalCategory.values().length + " categories of animals to choose from");
        // Prompts user for category choice in int form
        String category_choice = helper.input("Choose a category # of an animal: " + Arrays.toString(AnimalCategory.values()));
        // Temp AnimalCategory
        AnimalCategory category = null;
        // Checks if input category exists in AnimalCategory class
        for (AnimalCategory database_category : AnimalCategory.values()){
            if (database_category.name().equalsIgnoreCase(category_choice)){
                category = database_category;
            }
        }
        // Temp ArrayList<AnimalType>
        ArrayList<AnimalType> animals = new ArrayList<>();
        // Adds all animals of associated category to temp array list
        for(AnimalType animal: allAnimals){
            if(animal.getCategory() == category){
                animals.add(animal);
            }
        }
        // Prints number of animals to choose from temp array list
        System.out.println("There are " + animals.size() + " animals to choose from");
        // User choice to aggregate by pets
        boolean pet_choice = helper.inputYesNo("Do you want to see only pets?");
        // If user enters yes
        if(pet_choice){
            animals.removeIf(animal -> !animal.isPet());
            //Prints how many animals are available from aggregated animals array list
            System.out.println("There are " + animals.size() + " animals.");
            // Checks if animals is empty
            if (animals.isEmpty()){
                // Prints message to user
                System.out.println("There are no animals to choose from.");
                // Adds back in all animals into animals
                for(AnimalType animal: allAnimals){
                    if(animal.getCategory() == category){
                        animals.add(animal);
                    }
                }
            }
        }
        // If user enters no
        if(!pet_choice){
            // Temp boolean
            // User choice to aggregate by exotic animals
            boolean exotic_choice = helper.inputYesNo("Do you want to see only exotic animals?");
            //If user enters yes
            if(exotic_choice){
                animals.removeIf(animal -> animal.isPet());
                //Prints how many animals are available from aggregated animals array list
                System.out.println("There are " + animals.size() + " animals.");
                if (animals.isEmpty()){
                    // Prints message to user
                    System.out.println("There are no animals to choose from.");
                    // Adds back in all animals into animals
                    for(AnimalType animal: allAnimals){
                        if(animal.getCategory() == category){
                            animals.add(animal);
                        }
                    }
                }
            }
        }
        // Prints each of these animals and their type and category
        for (int i = 0; i < animals.size(); i++) {
            AnimalType animal = animals.get(i);
            System.out.println(i + " - " + animal.getType() + " : " + animal.getCategory().name());
        }
        // Prompts user to choose an animal
        int user_input = helper.inputInt("Choose an animal: ");
        // Converts user input into AnimalType using get method
        AnimalType animal_choice = animals.get(user_input);
        // Prompts user for a name
        String pet_name = helper.input("Enter a name: ");
        // Creates a new pet from AnimalType and String inputs
        Pet pet = new Pet(animal_choice, pet_name);
        // Adds pet to currentUser's profile
        currentUser.addPet(pet);
        // Congratulations message
        System.out.println("Congratulations, you have adopted a pet!");
        pet.toString();
    }

    // displayUser method.
    // No parameter.
    // Prompts user to input a username.
    // Checks to make sure user containing username input exists.
    // Prints user details and adopted pets.
    public void displayUser() {
        // Prompts user for username to display
        String user_input = helper.input("Enter the username of the user you want to display: ");
        // Temp user
        User database_user = null;
        // Checks if input user matches a user in userDatabase
        // Initial case
        for (User user : userDatabase) {
            if (user.getUserName().equalsIgnoreCase(user_input)) {
                database_user = user;
                break;
            }
        }
        // While loop for case when database_user ends up being null as a result of previous statement(s)
        while (database_user == null) {
            System.out.println("User not found.");
            user_input = helper.input("Enter the username of the user you want to display: ");
            for (User user : userDatabase) {
                if (user.getUserName().equalsIgnoreCase(user_input)) {
                    database_user = user;
                    break;
                }
            }
        }
        // Temp Pet[]
        Pet[] pets = database_user.getPetArray();
        // Prints user details
        System.out.println(database_user.getUserName() + " with birthday: " + database_user.getBday() + " Owns these pets: " + Arrays.toString(pets));
        // Prints all adopted pets of that user
            for (Pet pet : pets) {
                if (pet != null) {
                    String animalEmoji = pet.getType().getEmoji();
                    System.out.println(animalEmoji + " " + pet.getName() + " ( " + pet.getType().getCategory() + ")");
                }
            }
    }

    public static void main(String[] args){
        AnimalAdoptionSystem fb = new AnimalAdoptionSystem();
        fb.run();
    }
}


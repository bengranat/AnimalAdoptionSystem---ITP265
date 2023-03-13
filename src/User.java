/**
 * User class
 * Instance Variables:
 * String userName, String password, LocalDate bday, Pet[] pets, int numPets
 * Global Variables:
 * int numUsersCreated = 0, int DEFAULT_MAX_PETS = 5
 * @author Benjamin Granat
 * email: bgranat@usc.edu
 * ITP 265
 * Assignment 07
 */

import java.time.LocalDate;
import java.util.Arrays;

public class User {
    // Instance variables
    private String userName;
    private String password;
    private LocalDate bday;
    private Pet[] pets;
    private int numPets;
    // Global variables
    public static int numUsersCreated = 0;
    public static final int DEFAULT_MAX_PETS = 5;

    //Constructors
    public User(String userName, String password, LocalDate bday, Pet[] pets) {
        this.userName = userName;
        this.password = password;
        this.bday = bday;
        this.pets = pets;
        numPets = countPets(pets);
    }
    public User(String userName, String password, LocalDate bday) {
        this(userName, password, bday, new Pet[DEFAULT_MAX_PETS]);
    }

    public User(String name, String password, int year, int month, int day){
        this(name, password, LocalDate.of(year, month, day));
    }

    // Accessors and mutators
    public String getUserName() {
        return userName;
    }

    public LocalDate getBday() {
        return bday;
    }

    public int getNumPets() {
        return numPets;
    }

    // countPets method
    // parameter is Pet[]
    // counts all pets in given Pet[]; returns count
    private int countPets(Pet[] pets) {
        int count = 0;
        for(Pet pet: pets){
            if(pet != null){
                count++;
            }

        }
        return count;

    }
    // addPets method
    // parameter is Pet p
    // adds pet to Pet[] if range permits; returns boolean
    public boolean addPet(Pet p){
        if(numPets >= pets.length){
            return false;
        }
        else{
            pets[numPets] = p;
            numPets++;
            return true;
        }
    }
    // getPetArray method
    // No parameter
    // Returns pet array
    public Pet[] getPetArray(){
        // Temp int for all non-null pets
        int non_null_check = 0;
        // Removes null pets from indicies
        for(Pet pet: pets){
            if(pet != null){
                non_null_check++;
            }
        }
        // Creates new temp array
        // Stores # of non-null pets
        Pet[] pet_array = new Pet[non_null_check];
        int i = 0;
        // Adds all non-null pets into temp array
        for(Pet pet: pets){
            if(pet != null){
                pet_array[i] = pet;
                i++;
            }
        }
        // Returns temp array
        return pet_array;
    }
    // checkPassword method
    // String parameter
    // Returns boolean on whether input password matches user password
    public boolean checkPassword(String passwordAttempt){
        return password.equals(passwordAttempt);
    }
    // updatePassword method
    // Two string parameters
    // Updates old to new password
    public boolean updatePassword(String password, String newPassword){
        //check if the provided password  matches the saved one for the user
        if(this.password.equals(password)){
            this.password = newPassword;
            return true;
        }
        else{
            return false;
        }
    }
    // Equals and has methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return numPets == user.numPets && userName.equals(user.userName) && password.equals(user.password) && bday.equals(user.bday) && Arrays.equals(pets, user.pets);
    }

    @Override
    public int hashCode() {
        return 0;
    }
    // toString method
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", bday=" + bday +
                ", pets=" + Arrays.toString(pets) +
                ", numPets=" + numPets +
                '}';
    }
}

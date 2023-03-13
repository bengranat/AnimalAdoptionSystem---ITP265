/**
 * Enum LoginMenu class
 * Contains different LoginMenu prompts and several methods
 * @author Benjamin Granat
 * email: bgranat@usc.edu
 * ITP 265
 * Assignment 07
 */
public enum LoginMenu {
    MAKE_FAKE_ACCOUNTS("Make some fake accounts"),
    DISPLAY_USER_NAMES("Display user names"),
    CREATE_ACCOUNT("Create Account"),
    LOGIN("Login"),
    LOGOUT("Logout"),
    CHANGE_PASSWORD("Change password"),
    ADOPT_PET("Adopt a pet(requires login)"),
    DISPLAY_USER("Display user and their pets"),
    QUIT("Quit");

    // Instance variable
    private final String user_input;
    // Constructor
    LoginMenu(String user_input){
        this.user_input = user_input;
    }
    // getMenuString method
    // No parameters
    // Returns formatted string version of a menu
    public static String getMenuString(){
        String menu ="";
        for(LoginMenu user_input: LoginMenu.values()){
            menu += user_input.ordinal() + ": " + user_input + "\n";
        }
        return menu;
    }
    // getOptionNumber method
    // int parameter
    // returns enum value at index = parameter
    public static LoginMenu getOptionNumber(int n){
        return LoginMenu.values()[n];

    }

}


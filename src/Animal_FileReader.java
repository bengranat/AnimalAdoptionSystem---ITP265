import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Animal_FileReader class reads data from emojiAnimals.csv
 * Instance Variables:
 * String FILE_NAME_SIMPLE, String FILE_NAME_FULL
 * @author Benjamin Granat
 * email: bgranat@usc.edu
 * ITP 265
 * Assignment 07
 * P.S - When I was submitting this assignment, there was an error when importing the csv file.
 * It was working before, but now it has stopped working after I had changed something in the filreader file.
 * I do not remember what I changed and since I do not have enough time, I will submit as is. Please let me
 * know what the issue is in the feedback section. Thank you.
 */

public class Animal_FileReader {
    private static final String FILE_NAME_SIMPLE = "emojiAnimals.csv";
    private static final String FILE_NAME_FULL = "/Users/bgranat/Downloads/ITP265 Code/A07-Animal-User-System/src/emojiAnimals.csv";

    public static ArrayList<AnimalType>  readEmojiAnimalFile() {
        ArrayList<AnimalType> data = new ArrayList<>();
        boolean finished = false;
        String fileName = FILE_NAME_SIMPLE;
        while (!finished) {
            try (FileInputStream fis = new FileInputStream(fileName);
                 Scanner scan = new Scanner(fis)) {
                scan.nextLine(); // skip the header
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if (!line.isEmpty()) { //skip empty lines
                        AnimalType a = parseLine(line);
                        data.add(a);
                    }
                }
                finished = true;
            } catch (FileNotFoundException e) {
                finished = false; // something went wrong!
                System.err.println("File not found: " + fileName);
                if (!fileName.equalsIgnoreCase(FILE_NAME_FULL)) {
                    fileName = FILE_NAME_FULL;
                } else {
                    System.out.println("Already tried alternate file name. Exiting program.");
                    e.printStackTrace();
                    System.exit(1);
                }

            } catch (IOException e) {
                System.err.println("An IOException occured. Can't recover, ending program. ");
                e.printStackTrace();
                System.exit(1);
            }
        }
        return data;
    }

    private static AnimalType parseLine(String line) {
        Scanner ls = new Scanner(line);
        ls.useDelimiter(",");
        String type = ls.next();
        String cat = ls.next();
        String emoji = ls.next();
        String pet = ls.next();
        boolean isPet = false;
        if(pet.equalsIgnoreCase("yes")){
            isPet = true;
        }
        AnimalCategory category = AnimalCategory.valueOf(cat.toUpperCase());

        return new AnimalType(type, category, emoji, isPet);
    }
}


	

/**
 * AnimalType class
 * Instance Variables:
 * String type, AnimalCategory category, String emoji, boolean isPet
 * @author Benjamin Granat
 * email: bgranat@usc.edu
 * ITP 265
 * Assignment 07
 */

import java.util.Objects;

public class AnimalType {
    // Instance Variables
    private String type;
    private AnimalCategory category;
    private String emoji;
    private boolean isPet;

    //Constructors
    public AnimalType(String type, AnimalCategory category, String emoji, boolean isPet) {
        this.type = type;
        this.category = category;
        this.emoji = emoji;
        this.isPet = isPet;
    }
    //Accessors and mutators
    public String getType() {
        return type;
    }

    public String getEmoji() {
        return emoji;
    }

    public AnimalCategory getCategory() {
        return category;
    }

    public boolean isPet() {
        return isPet;
    }
    //Equals and hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalType that = (AnimalType) o;
        return isPet == that.isPet && type.equals(that.type) && category.equals(that.category) && emoji.equals(that.emoji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, category, emoji, isPet);
    }
    //toString method
    @Override
    public String toString() {
        return "AnimalType{" +
                "type='" + type + '\'' +
                ", category=" + category +
                ", emoji='" + emoji + '\'' +
                ", isPet=" + isPet +
                '}';
    }
}

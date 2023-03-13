/**
 * Pet class
 * Instance Variables:
 * AnimalType type, String name, LocalDate adoptedDay
 * @author Benjamin Granat
 * email: bgranat@usc.edu
 * ITP 265
 * Assignment 07
 */

import java.time.LocalDate;
import java.util.Objects;

public class Pet {
    //Instance variables
    private AnimalType type;
    private String name;
    private LocalDate adoptedDay;

    //Constructors
    public Pet(AnimalType type, String name, LocalDate adoptedDay) {
        this.type = type;
        this.name = name;
        this.adoptedDay = adoptedDay;
    }

    public Pet(AnimalType type, String name) {
        this(type, name, LocalDate.now());
    }

    //Accessors and mutators
    public AnimalType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public LocalDate getAdoptedDay() {
        return adoptedDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    //toString, equals, and hash methods
    @Override
    public String toString() {
        return type.getEmoji() + " " + name + " the " + type.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return type.equals(pet.type) && name.equals(pet.name) && adoptedDay.equals(pet.adoptedDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, adoptedDay);
    }
}

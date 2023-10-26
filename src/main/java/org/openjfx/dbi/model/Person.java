package org.openjfx.dbi.model;

public class Person {
    private int id;
    private String lastName;
    private String firstName;

    public Person(int id, String lastName, String firstName) {
        this(lastName, firstName);
        this.id = id;
    }

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastNname(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return lastName;
    }

}

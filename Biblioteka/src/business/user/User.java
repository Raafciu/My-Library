package business.user;

import java.util.Objects;

public class User {

    private String pesel;
    private String firstName;
    private String lastName;
    private int age;
    private int numberOfBorrowBooks;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberOfBorrowBooks() {
        return numberOfBorrowBooks;
    }

    public void setNumberOfBorrowBooks(int numberOfBorrowBooks) {
        this.numberOfBorrowBooks = numberOfBorrowBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                numberOfBorrowBooks == user.numberOfBorrowBooks &&
                Objects.equals(pesel, user.pesel) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, firstName, lastName, age, numberOfBorrowBooks);
    }

    @Override
    public String toString() {
        return "User{" +
                "pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", numberOfBorrowBooks=" + numberOfBorrowBooks +
                '}';
    }
}

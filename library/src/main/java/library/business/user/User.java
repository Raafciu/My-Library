package library.business.user;

import library.business.common.ALibraryUser;

import java.util.Objects;

public class User extends ALibraryUser {

    private int numberOfBorrowBooks;

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

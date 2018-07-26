package presentation;

import control.exception.KeyNotFoundException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static presentation.employee.EmployeeView.employeeView;
import static presentation.user.UserView.userView;

public class Library {

    public static void main(String[] args) throws KeyNotFoundException, InterruptedException {
        int choose;
        do {
            System.out.println("*********WITAMY W NASZEJ BIBLIOTECE*********");
            System.out.println("Wybierz tryb:");
            System.out.println("1.Uzytkownik");
            System.out.println("2.Pracownik");
            System.out.println("3.Koniec");
            Scanner scChoose = new Scanner(System.in);
            choose = scChoose.nextInt();

            switch (choose) {
                case 1:
                    userView();
                    break;
                case 2:
                    employeeView();
                    break;

                case 3:
                    System.out.println("Do widzenia!");
                    System.exit(-1);
                    break;
                default:
                    System.out.println("Nie ma takiej operacji! Wybierz operacje 1-3:");
            }
        } while (true);
    }

    public static void timeSeparator() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }
}

package main.notification;

import java.util.Scanner;

public class Notification {

    public static boolean confirmDeleteOperation() {
        System.out.println("Do you really want to delete the record? (y/n)");
        Scanner scanner = new Scanner(System.in);
        return scanner.next().equals("y");
    }

}

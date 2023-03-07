package contacts_1;

import contacts_1.logic.*;
import contacts_1.ui.Application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputValidationException {
        Scanner scanner = new Scanner(System.in);
        PhoneBook pb = new PhoneBook("phone.db");
        Application application = new Application(scanner, pb);
        application.start();
    }
}

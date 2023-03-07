package contacts_1.ui;


import contacts_1.domain.*;
import contacts_1.logic.*;


import java.util.Scanner;

public class Application {
    private final Scanner scanner;
    private final PhoneBook phoneBook;

    public Application(Scanner scanner, PhoneBook phoneBook) {
        this.scanner = scanner;
        this.phoneBook = phoneBook;
    }

    public void start() throws InputValidationException {
        while (true) {
            try {
                System.out.print("[menu] Enter action (add, list, search, count, exit):");
                MenuOption action = MenuOption.valueOf(scanner.nextLine().toUpperCase());
                switch (action) {
                    case ADD -> addCommand();
                    case LIST -> listCommand();
                    case SEARCH -> searchCommand();
                    case COUNT -> countCommand();
                    case EXIT -> {
                        this.phoneBook.serialize();
                        System.exit(0);
                    }
                }
            } catch (IllegalArgumentException ie) {
                System.out.println("Invalid command!");
            }
            System.out.println();
        }
    }

    public void addCommand() throws InputValidationException {
        System.out.print("Enter the type (person, organization):");
        ContactType type = ContactType.valueOf(scanner.nextLine().toUpperCase());
        try {
            switch (type) {
                case PERSON -> this.phoneBook.addContact(createPersonalContact());
                case ORGANIZATION -> this.phoneBook.addContact(createOrganisationContact());
            }
            this.phoneBook.serialize();
        } catch (IllegalArgumentException ie) {
            System.out.println("Invalid type!");
        }
        System.out.println();
    }

    public void listCommand() throws InputValidationException {
        // read data from file - serialize
        this.phoneBook.loadDbContact();
        if (!this.phoneBook.hasAnyContact())
            throw new InputValidationException("No records!");
        System.out.println(this.phoneBook);
        System.out.println();
        System.out.println("[list] Enter action ([number], back):");
        String input = scanner.nextLine().trim();
        Action action = Action.actionFromInput(input);
        if (action != null) {
            switch (action) {
                case NUMBER -> numberCommand(input);
                case BACK -> start();
                default -> System.out.println("Invalid action!");
            }
        }
        System.out.println();
    }

    public void searchCommand() throws InputValidationException {
        System.out.print("Enter search query:");
        String txt = scanner.nextLine();
        this.phoneBook.loadDbContact();
        this.phoneBook.searchContact(txt);
        System.out.println();
        System.out.print("[search] Enter action ([number], back, again): ");
        String input = scanner.nextLine().trim();

        Action action = Action.actionFromInput(input);
        if (action != null) {
            switch (action) {
                case NUMBER -> numberCommand(input);
                case BACK -> start();
                case AGAIN -> searchCommand();
                default -> System.out.println("Invalid action!");
            }
        }
        System.out.println();
    }

    public void countCommand() {
        System.out.println("The Phone Book has " + this.phoneBook.countContact()+ " records.");
        System.out.println();
    }

    private void numberCommand(String input) throws InputValidationException {
        Contact contact = this.phoneBook.getContact(Integer.parseInt(input));
        System.out.println(contact);
        System.out.println();
        System.out.print("[record] Enter action (edit, delete, menu):");
        String userInput = scanner.nextLine();
        FieldOption option = FieldOption.valueOf(userInput.toUpperCase());
        switch (option) {
            case EDIT -> editCommand(contact);
            case MENU -> start();
            case DELETE -> deleteCommand(contact);
            default -> System.out.println("Invalid field!");
        }
        System.out.println();
    }

    private void editCommand(Contact contact) {
        ContactType type = contact.getContactType();
        if (type == ContactType.PERSON)
            System.out.println("Select a field (name, surname, birth, gender, number): ");
        else if (type == ContactType.ORGANIZATION)
            System.out.println("Select a field (name, address, number): ");

        String field = scanner.nextLine();
        System.out.println("Enter " + field + ": ");
        String fieldValue = scanner.nextLine();
        this.phoneBook.editContact(contact, field, fieldValue);
        this.phoneBook.serialize();
        System.out.println();
    }

    private void deleteCommand(Contact contact) {
        this.phoneBook.deleteContact(contact);
        this.phoneBook.serialize();
        System.out.println();
    }


    public PersonalContact createPersonalContact() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the birth date: ");
        String birthdate = Validate.formatDate(scanner.nextLine());
        System.out.print("Enter the gender (M, F): ");
        String gender = Validate.formatGender(scanner.nextLine());
        System.out.print("Enter the number: ");
        String phone = Validate.formatPhone(scanner.nextLine());
        return new PersonalContact(name, surname, birthdate, gender, phone);
    }

    public OrganisationContact createOrganisationContact() {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String phone = Validate.formatPhone(scanner.nextLine());
        return new OrganisationContact(name, address, phone);
    }
}

package contacts;


import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private PhoneBook phoneBook;
    public UserInterface(Scanner scanner, PhoneBook phoneBook) {
        this.scanner = scanner;
        this.phoneBook = phoneBook;
    }

    public void start()  {
        while (true) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            String userCommand = scanner.nextLine();
            if (userCommand.equals("exit")) {
                break;
            }
            try {
                process(userCommand);
            } catch (InputValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void process(String command) throws InputValidationException {
        switch (command) {
            case "add" -> addCommand();
            case "remove" -> removeCommand();
            case "edit" -> editCommand();
            case "info" -> infoCommand();
            case "count" -> countCommand();
            default -> System.out.println("Invalid command!");
        }
    }

    public void addCommand() throws InputValidationException {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        switch (type) {
            case "person" -> addPerson();
            case "organization" -> addOrganisation();
            default -> System.out.println("Invalid contact type!");
        }
        System.out.println();
    }

    public void addPerson() throws InputValidationException {
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
        this.phoneBook.addContact(new PersonalContact(name, surname, birthdate, gender, phone));
    }

    public void addOrganisation() throws InputValidationException {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String phone = Validate.formatPhone(scanner.nextLine());
        this.phoneBook.addContact(new OrganisationContact(name, address, phone));
    }

    public void removeCommand() throws InputValidationException {
        if (!this.phoneBook.hasAnyContact())
            throw new InputValidationException("No records to remove!");
        System.out.println(this.phoneBook.toString());
        System.out.print("Select a record: ");
        int record = Integer.parseInt(scanner.nextLine());
        this.phoneBook.removeContact(record);
        System.out.println();
    }

    public void editCommand() throws InputValidationException {
        if (!this.phoneBook.hasAnyContact())
            throw new InputValidationException("No records to edit!");
        System.out.println(this.phoneBook.toString());
        System.out.println("Select a record: ");
        int record = Integer.parseInt(scanner.nextLine());
        Contact checkContact = this.phoneBook.getContact(record);
        if (checkContact instanceof PersonalContact) {
            System.out.println("Select a field (name, surname, birth, gender, number): ");
        } else if (checkContact instanceof OrganisationContact) {
            System.out.println("Select a field (address, number): ");
        }
        String field = scanner.nextLine();
        System.out.println("Enter " + field + ": ");
        String fieldValue = scanner.nextLine();
        this.phoneBook.editContact(record, field, fieldValue);
        System.out.println();
    }

    public void infoCommand() {
        System.out.println(this.phoneBook.toString());
        System.out.print("Enter index to show info: ");
        int record = Integer.parseInt(scanner.nextLine());
        System.out.println(this.phoneBook.getContact(record));
        System.out.println();
    }

    public void countCommand() {
        System.out.println("The Phone Book has " +this.phoneBook.countContact() + " records.");
        System.out.println();
    }
}

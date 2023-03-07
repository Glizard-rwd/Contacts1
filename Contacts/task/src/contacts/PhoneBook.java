package contacts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class PhoneBook {
    private List<Contact> phoneBook;
    public PhoneBook() {
        this.phoneBook = new ArrayList<>();
    }

    // add successfully or not
    public void addContact(Contact newContact) throws InputValidationException {
        if (this.phoneBook.contains(newContact)) {
            throw new InputValidationException("Already add!");
        }
        this.phoneBook.add(newContact);
        System.out.println("The recorded added.");
    }

    public boolean hasAnyContact() {
        return !this.phoneBook.isEmpty();
    }

    public Contact getContact(int record) {
        if (Validate.isValidInput(record, this.phoneBook.size())) {
            return this.phoneBook.get(record-1);
        }
        return null;
    }
    public void removeContact(int record) {
        if (Validate.isValidInput(record, this.phoneBook.size())) {
            Contact toRemove = this.phoneBook.get(record - 1);
            this.phoneBook.remove(toRemove);
            System.out.println("The record removed!");
        }
    }

    public void editContact(int record, String field, String updateValue) {
        if (Validate.isValidInput(record, this.phoneBook.size())) {
            Contact toEdit = this.phoneBook.get(record-1);
            if (toEdit instanceof PersonalContact) {
                switch (field) {
                    case "name" -> toEdit.setName(updateValue);
                    case "surname" -> ((PersonalContact) toEdit).setSurname(updateValue);
                    case "birth" -> ((PersonalContact) toEdit).setBirthDate(updateValue);
                    case "gender" -> ((PersonalContact) toEdit).setGender(updateValue);
                    case "number" -> toEdit.setPhone(updateValue);
                    default -> System.out.println("Invalid field in personal contact!");
                }
            } else if (toEdit instanceof OrganisationContact) {
                switch (field) {
                    case "address" -> ((OrganisationContact) toEdit).setAddress(updateValue);
                    case "number" -> toEdit.setPhone(updateValue);
                    default -> System.out.println("Invalid field in organisation contact!");
                }
            }
            toEdit.setEditTime(LocalDateTime.now());
            System.out.println("The record updated!");
        }
    }

    public int countContact() {
        return this.phoneBook.size();
    }

    @Override
    public String toString() {
        StringBuilder pb = new StringBuilder(new StringBuilder());
        for (Contact c: this.phoneBook) {
            if (c.equals(this.phoneBook.get(this.phoneBook.size() - 1)))
                pb.append(this.phoneBook.indexOf(c)+1).append(". ").append(c.getFullName());
            else
                pb.append(this.phoneBook.indexOf(c)+1).append(". ").append(c.getFullName()).append("\n");
        }
        return pb.toString();
    }
}

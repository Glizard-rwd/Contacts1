package contacts_1.logic;
import contacts_1.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBook {
    private List<Contact> phonebook;
    private final String fileName;

    public PhoneBook(String fileName) {
        this.phonebook = new ArrayList<>();
        this.fileName = fileName;
    }

    // ADD - menu option

    public void addContact(Contact newContact) throws InputValidationException {
        if (this.phonebook.contains(newContact)) {
            throw new InputValidationException("Already add!");
        }
        this.phonebook.add(newContact);
        System.out.println("The recorded added.");
    }

    // check phonebook is not empty
    public boolean hasAnyContact() {
        return !this.phonebook.isEmpty();
    }


    // get contact by id (index)
    public Contact getContact(int record) {
        if (Validate.isValidInput(record, this.phonebook.size())) {
            return this.phonebook.get(record-1);
        }
        return null;
    }

    // DELETE - field option
    public void deleteContact(Contact toRemove) {
        this.phonebook.remove(toRemove);
        System.out.println("The record removed!");
    }

    // EDIT - field option
    public void editContact(Contact contact, String field, String updateValue) {
        ContactType type = contact.getContactType();
        switch (type) {
            case PERSON:
                switch (field) {
                    case "name" -> contact.setName(updateValue);
                    case "surname" -> ((PersonalContact) contact).setSurname(updateValue);
                    case "birth" -> ((PersonalContact) contact).setBirthDate(updateValue);
                    case "gender" -> ((PersonalContact) contact).setGender(updateValue);
                    case "number" -> contact.setPhone(updateValue);
                    default -> System.out.println("Invalid field in personal contact!");
                }
                break;
            case ORGANIZATION:
                switch (field) {
                    case "name" -> contact.setName(updateValue);
                    case "address" -> ((OrganisationContact) contact).setAddress(updateValue);
                    case "number" -> contact.setPhone(updateValue);
                    default -> System.out.println("Invalid field in organisation contact!");
                }
                break;
        }
        contact.setEditTime(LocalDateTime.now());
        System.out.println("Saved");
        System.out.println(contact);
    }

    // COUNT - menu option

    public int countContact() {
        return this.phonebook.size();
    }


    // LIST - menu option


    // SEARCH - menu option
//    public void searchContact(String txt) {
//        List<Contact> searchResult = this.phonebook.stream().filter(contact ->
//                contact.getFullName().toLowerCase().contains(txt.trim())).toList();
//        System.out.println("Found " + searchResult.size() + " results:");
//        searchResult.forEach(c -> System.out.println(searchResult.indexOf(c)+1 +". " + c.getFullName()));
//    }

    public void searchContact(String text) {
        List<Contact> searchResult = this.phonebook.stream().filter(contact ->
                contact.getFullName().toLowerCase().contains(text.trim()) ||
                contact.getPhone().contains(text.trim()) ||
                (contact.getContactType() == ContactType.PERSON && ((PersonalContact) contact).getBirthDate().contains(text.trim())) ||
                (contact.getContactType() == ContactType.PERSON && ((PersonalContact) contact).getGender().contains(text.trim())) ||
                (contact.getContactType() == ContactType.ORGANIZATION && ((OrganisationContact) contact).getAddress().contains(text.trim()))).toList();
        System.out.println("Found " + searchResult.size() + " results:");
        searchResult.forEach(c -> System.out.println(searchResult.indexOf(c)+1 +". " + c.getFullName()));
    }


    // SERIALIZE - write object to file
    // write contact list to file
    public void serialize() {
        if (null != this.fileName) {
            SerializableUtil.writeOtoFile(this.phonebook, this.fileName);
        }
    }

    public void loadDbContact() {
        @SuppressWarnings("unchecked")
        List<Contact> dbContacts = (List<Contact>) SerializableUtil.readToObject(this.fileName);
        this.phonebook = dbContacts;
    }


    @Override
    public String toString() {
        StringBuilder pb = new StringBuilder(new StringBuilder());
        for (Contact c: this.phonebook) {
            if (c.equals(this.phonebook.get(this.phonebook.size() - 1)))
                pb.append(this.phonebook.indexOf(c)+1).append(". ").append(c.getFullName());
            else
                pb.append(this.phonebook.indexOf(c)+1).append(". ").append(c.getFullName()).append("\n");
        }
        return pb.toString();
    }
}


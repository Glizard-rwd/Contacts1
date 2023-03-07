package contacts_1.domain;


import contacts_1.logic.Validate;

public class PersonalContact extends Contact {
    private String surname;
    private String birthDate;
    private String gender;
    public PersonalContact(String name, String surname, String birthDate, String gender, String phone) {
        super(name, phone);
        this.setSurname(surname);
        this.setBirthDate(birthDate);
        this.setGender(gender);
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {

        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = Validate.formatName(surname);
    }

    @Override
    public String getFullName() {
        return super.getName() + " " + this.surname;
    }

    @Override
    public String toString() {
        return "Name: " + super.getName() +
                "\nSurname: " + this.surname +
                "\nBirth date: " + this.birthDate +
                "\nGender: " + this.gender +
                "\n" + super.toString();
    }
}

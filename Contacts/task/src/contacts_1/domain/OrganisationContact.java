package contacts_1.domain;

public class OrganisationContact extends Contact {
    private String address;
    public OrganisationContact(String name, String address, String phone) {
        super(name, phone);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Organization name: " + super.getName() +
                "\nAddress: " + this.address +
                "\n" + super.toString();
    }

    @Override
    public String getFullName() {
        return super.getName();
    }
}

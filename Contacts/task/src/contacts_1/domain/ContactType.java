package contacts_1.domain;

public enum ContactType {
    PERSON("person"), ORGANIZATION("organization");
    public final String type;
    ContactType(String type) {
        this.type = type;
    }

    public String getContactType() {
        return this.type;
    }
}

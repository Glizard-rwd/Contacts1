package contacts_1.domain;

public enum FieldOption {
    EDIT("edit"),
    DELETE("delete"),
    MENU("menu");
    private final String option;
    FieldOption(String option) {
        this.option = option;
    }
    public String getAction() {
        return this.option;
    }
}

package contacts_1.domain;

public enum MenuOption {
    ADD("add"), LIST("list"), SEARCH("search"), COUNT("count"), EXIT("exit");
    private final String option;
    MenuOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return this.option;
    }
}

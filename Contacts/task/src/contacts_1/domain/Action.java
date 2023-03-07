package contacts_1.domain;

public enum Action {
    NUMBER("[number]"), BACK("back"), AGAIN("again");
    private final String action;
    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    public static Action actionFromInput(String action) {
        for (Action a: Action.values()) {
            if (a.getAction().equals(action)) {
                return a;
            }
        }

        if (action.matches("\\d+")) {
            return NUMBER;
        }
        return null;
    }
}

package main.java;

public enum StudentCondition {
CATCH_UP("catch-up"),
    SICK("sick"),
    ABSENT("absent"),
    PRESENT("present");
private String condition;

    StudentCondition(String condition) {
        this.condition=condition;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "StudentCondition{" +
                "condition='" + condition + '\'' +
                '}';
    }
}

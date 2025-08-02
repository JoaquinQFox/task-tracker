

public enum  TaskStatus {
    NONE, TO_DO, IN_PROGRESS, DONE;

    public String getStatus() {
        return switch (this) {
            case TO_DO -> "to do";
            case IN_PROGRESS -> "in progress";
            case DONE -> "done";
            default -> null;
        };
    }
}

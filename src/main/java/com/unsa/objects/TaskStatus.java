package com.unsa.objects;

public enum  TaskStatus {
    TO_DO, IN_PROGRESS, DONE;

    public String getStatus() {
        return switch (this) {
            case TO_DO -> "small";
            case IN_PROGRESS -> "in progress";
            case DONE -> "done";
            default -> null;
        };
    }
}

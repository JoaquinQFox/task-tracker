package com.unsa.objects;

import java.time.LocalDateTime;

public class Task {
    private static int globalId = 0;

    private int id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task() {
        this("No description");
    }

    public Task(String description) {
        this.id = setGlobalId();
        this.description = description;
        this.status = TaskStatus.TO_DO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private int setGlobalId() {
        globalId++;
        return globalId;
    }

    public String toJson() {
        return "{" +
            "\"id\":" + getId() + "," + 
            "\"description\":" + "\"" + getDescription() + "\"," +
            "\"status\":" + "\"" + status.name() + "\"," +
            "\"createdAt\":\"" + getCreatedAt() + "\"," +
            "\"updatedAt\":\"" + getUpdatedAt() + "\"" + 
            "}";
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return status.getStatus();
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
}

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
        this.id = globalId();
        this.description = description;
        this.status = TaskStatus.TO_DO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Task(int id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        updateGlobalId(id);
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Task taskFromJson(String jsonText) {
        int id = Integer.parseInt(getJsonValue("id", jsonText));
        String description = getJsonString("description", jsonText);
        TaskStatus status = TaskStatus.valueOf(getJsonString("status", jsonText));
        LocalDateTime createdAt = LocalDateTime.parse(getJsonString("createdAt", jsonText));
        LocalDateTime updatedAt = LocalDateTime.parse(getJsonString("updatedAt", jsonText));
        return new Task(id, description, status, createdAt, updatedAt);
    }

    private static String getJsonValue(String field, String jsonText) {
        String pattern = "\"" + field + "\":";
        int start = jsonText.indexOf(pattern) + pattern.length();
        int end = jsonText.indexOf(",", start);
        if (end == -1)
            end = jsonText.indexOf("}");
        return jsonText.substring(start, end);
    }

    private static String getJsonString(String field, String jsonText) {
        String pattern = "\"" + field + "\":\"";
        int start = jsonText.indexOf(pattern) + pattern.length();
        int end = jsonText.indexOf("\"", start);
        return jsonText.substring(start, end);
    }

    private int globalId() {
        globalId++;
        return globalId;
    }

    private void updateGlobalId(int id) {
        globalId = id++;
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

    public TaskStatus getTaskStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Task: ID:" + getId() + ", Description: " + getDescription() + ", Status: " + getStatus();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

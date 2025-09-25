package com.example.mspl_connect.Entity;

public class InterviewPermissionRequest {
    private String userId;
    private String permissionType; // To indicate the type of permission, e.g., "interviewAccess" or "salesAccess"
    private boolean enabled;

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}


package com.example.demo.Model;

import java.util.List;

public class ImportResult {
    private int successCount;
    private int failedCount;
    private List<String> errors;

    public void addError(String error) {
        errors.add(error);
        failedCount++;
    }
    public void addSuccess() {
        successCount++;
    }
    // Getters and Setters
    public int getSuccessCount() { return successCount; }
    public void setSuccessCount(int successCount) { this.successCount = successCount; }
    public int getFailedCount() { return failedCount; }
    public void setFailedCount(int failedCount) { this.failedCount = failedCount; }
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}

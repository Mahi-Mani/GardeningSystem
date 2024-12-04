package com.gardensimulation;

public class LogMessage {
    private String message;
    private String severity;

    public LogMessage(String message, String severity) {
        this.message = message;
        this.severity = severity;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSeverity() {
        return this.severity;
    }
}

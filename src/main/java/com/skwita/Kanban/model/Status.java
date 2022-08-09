package com.skwita.Kanban.model;

public enum Status {
    TODO("To Do"),
    INPROGRESS("In Progress"),
    DONE("Done");

    private final String displayValue;

    Status(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

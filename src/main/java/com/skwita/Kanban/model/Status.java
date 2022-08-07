package com.skwita.Kanban.model;

public enum Status {
    TODO("todo"),
    INPROGRESS("inprogress"),
    DONE("done");

    private final String displayValue;

    private Status(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

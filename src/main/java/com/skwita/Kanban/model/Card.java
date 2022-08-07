package com.skwita.Kanban.model;

import lombok.Data;

@Data
public class Card {
    private long id;
    private String name;
    private String description;
    private Status status;
    private String person;

    public Card(String name, String description, Status status, String person) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.person = person;
    }
}

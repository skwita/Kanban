package com.skwita.Kanban.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

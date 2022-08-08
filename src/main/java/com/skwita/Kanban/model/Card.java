package com.skwita.Kanban.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
public class Card {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotEmpty(message = "Name must not be empty")
    @Size(max=100, message = "Name must must not be more than 100 characters long")
    private String name;
    @NotNull
    @NotEmpty(message = "Description must not be empty")
    @Size(max=300, message="Description must not be more than 300 characters long")
    private String description;
    private Status status;
    @NotNull
    @NotEmpty(message = "Persons name must not be empty")
    @Size(max=100, message = "Persons name must must not be more than 100 characters long")
    private String person;

    public Card(String name, String description, Status status, String person) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.person = person;
    }
}

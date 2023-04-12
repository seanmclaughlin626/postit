package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Forum {
    private int id;
    private String name;
    private LocalDateTime lastInteraction;

    public Forum() {
    }

    public Forum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastInteraction() {
        return lastInteraction;
    }

    public void setLastInteraction(LocalDateTime lastInteraction) {
        this.lastInteraction = lastInteraction;
    }
}

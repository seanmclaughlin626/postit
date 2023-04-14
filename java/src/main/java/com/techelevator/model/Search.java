package com.techelevator.model;

public class Search {
    private String searchInput;

    public Search(String searchInput) {
        this.searchInput = searchInput;
    }

    public Search() {
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }
}

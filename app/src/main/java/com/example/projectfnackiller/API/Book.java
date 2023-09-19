package com.example.projectfnackiller.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings

public class Book implements Serializable{
    String title;
    String language;
    String date = "N/A";
    ArrayList<String> categories = new ArrayList<String>();
    ArrayList<String> authors = new ArrayList<String>() {
    };
    HashMap<Library, Integer> libraries = new HashMap<Library, Integer>();
    String type = "N/A";

    public Book(String title) {
        fixTitle(title);
    }

    public void fixTitle(String tile) {
        if (tile.contains("|")) {
            String[] parts = tile.split("\\|");
            this.title = parts[0];
        } else {
            this.title = tile;
        }
    }


    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        fixTitle(title);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addCategory(String category) {
        if (!category.equals("pas de code stat 2")) {
            String[] parts = category.split(" ", 2);
            categories.add(parts[1]);
        }
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void addAuthor (String authorName) {
        authors.add(authorName);
    }

    public HashMap<Library, Integer> getLibraries() {
        return libraries;
    }

    public void addToLibrary(Library l, int numberOfCopies) {
        libraries.put(l, numberOfCopies);
    }
}

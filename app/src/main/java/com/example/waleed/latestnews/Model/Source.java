package com.example.waleed.latestnews.Model;

import java.util.List;


public class Source {
    private String id;
    private String Name;
    private String Description;
    private String Url;
    private String Category;
    private String Language;
    private String Country;


    public Source() {
    }

    public Source(String id, String name, String description, String url, String category, String language, String country) {
        this.id = id;
        Name = name;
        Description = description;
        Url = url;
        Category = category;
        Language = language;
        Country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }


}

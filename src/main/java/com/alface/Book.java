package com.alface;

public class Book {
    protected String name;
    protected String author;
    protected int pages;
    protected int readPages;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getReadPages() {
        return readPages;
    }
    public void setReadPages(int readPages) {
        this.readPages = readPages;
    }
    public String getPercent()
    {
        double p = this.readPages / this.pages;
        return (p * 100) + "%";
    }
    
}
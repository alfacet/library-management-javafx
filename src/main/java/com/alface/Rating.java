package com.alface;
public class Rating {
    public String author;
    public String title;
    public String commentary;
    public double rating;
    public Rating()
    {
        
    }
    public Rating(String a, String t, String c, double r)
    {
        this.author = a;
        this.title = t;
        this.commentary = c;
        this.rating = r;
    }    
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    
} 

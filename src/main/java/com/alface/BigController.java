package com.alface;

import java.util.ArrayList;

public class BigController {
    // Quando for colocar imagens, usa o path + o nome da imagem pra ficar mais
    // fácil c:
    private String pathCss;
    private String pathImages;
    
    private int bookIndex;
    public BigController() {
        setPath();
    }

    public void setPath() {
        if (System.getProperty("os.name").toString().equals("Linux")) {
            String bruh = System.getProperty("user.dir");
            String bruh2 = "";
            int index = 0;
            //Fiz... Fiz apenas o que tinha de ser feito. 
            if (bruh.startsWith("/"))
                index = 1;
            
            for (int i = index; i < bruh.length(); i++) 
                bruh2 += bruh.charAt(i);
            
            this.pathImages = "file:///" + bruh2 + "/src/main/images/";
            this.pathCss = System.getProperty("user.dir") + "/src/main/java/css/";

        } else {
            this.pathImages = "file:\\\\\\" + System.getProperty("user.dir") + "\\src\\main\\images\\";
            this.pathCss = System.getProperty("user.dir") + "\\src\\main\\java\\css\\";
        }
    }

    public String getPathImages() {
        return this.pathImages;
    }

    public String getPathCss() {
        return this.pathCss;
    }

    public int getBookIndex() {
        return bookIndex;
    }

    public void setBookIndex(int bookIndex) {
        this.bookIndex = bookIndex;
    }

}
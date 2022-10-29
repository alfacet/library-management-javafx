package com.alface;

public class BigController {
    // Quando for colocar imagens, usa o path + o nome da imagem pra ficar mais
    // fácil c:
    private String pathCss;
    private String pathImages;
    
    public BigController() {
        setPath();
    }

    public String setPath() {
        if (System.getProperty("os.name").toString().equals("Linux")) {
            pathImages = "file://" + System.getProperty("user.dir") + "/src/main/images/";
            pathCss = System.getProperty("user.dir") + "/src/main/java/css/";

            return pathImages;
        } else {
            pathImages = "file:\\\\\\" + System.getProperty("user.dir") + "\\src\\main\\images\\";
            pathCss = System.getProperty("user.dir") + "\\src\\main\\java\\css\\";

            return pathImages;
        }
    }

    public String getPathImages()
    {
        return pathImages;
    }
    public String getPathCss()
    {
        return pathCss;
    }
}
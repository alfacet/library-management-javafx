package com.alface;

public class BigController {
    // Quando for colocar imagens, usa o path + o nome da imagem pra ficar mais
    // fácil c:
    protected String pathCss;
    protected String pathImages;
    
    public BigController() {
      if(System.getProperty("os.name") == "Linux")
       {
        pathImages = System.getProperty("user.dir") + "/src/main/images/";
        pathCss = System.getProperty("user.dir") + "/src/main/java/css/";
       }
       else
       {
        pathImages = "file:\\\\\\" + System.getProperty("user.dir") + "\\src\\main\\images\\";
        pathCss = System.getProperty("user.dir") + "\\src\\main\\java\\css\\";
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
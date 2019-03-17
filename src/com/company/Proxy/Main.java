package com.company.Proxy;

public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("D:\\image\\...");
        image.display();
    }
}

interface Image {
    void display();
}

class RealImage implements Image {
    private String file;

    public RealImage(String file) {
        this.file = file;
        load();
    }

    void load() {
        System.out.println("Downloading : " + file);
    }

    @Override
    public void display() {
        System.out.println("Watching : " + file);
    }
}

class ProxyImage implements Image {
    private String file;
    private RealImage realImage;

    public ProxyImage(String file) {
        this.file = file;
    }


    @Override
    public void display() {
        if (realImage == null)
            realImage = new RealImage(file);
        realImage.display();
    }
}

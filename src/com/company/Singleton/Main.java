package com.company.Singleton;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int SIZE = 1000;

        /*Singleton arr[] = new Singleton[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = Singleton.getInstance();
        }
        System.out.println(Singleton.counter);*/

        Thread t[] = new Thread[SIZE];
        for (int i = 0; i < SIZE; i++) {
            t[i] = new Thread(new R());
            t[i].start();
        }
        for (int i = 0; i < SIZE; i++) {
            t[i].join();
        }
        System.out.println(Singleton.counter);
    }
}

class R implements Runnable {

    @Override
    public void run() {
        Singleton.getInstance();
    }
}

class Singleton {
    public static int counter = 0;
    private static volatile Singleton instance = null;

    private Singleton() {
        counter++;
    }

    /*public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }*/

    public static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance ==null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
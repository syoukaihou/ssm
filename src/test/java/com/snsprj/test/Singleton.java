package com.snsprj.test;

public class Singleton {

    private Singleton() {
    }

    private static Singleton singleton;

    /**
     * 线程安全的懒汉模式
     * @return Singleton
     */
    public static Singleton getSingleton() {

        synchronized(Singleton.class){
            if (singleton == null) {
                System.out.println("singleton is null");
                singleton = new Singleton();
            }else{
                System.out.println("singleton is not null");
            }
        }

        return singleton;

    }
}

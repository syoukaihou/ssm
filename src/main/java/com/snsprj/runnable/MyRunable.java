package com.snsprj.runnable;

/**
 * Created by skh on 2017/7/25.
 */
public class MyRunable implements Runnable {

    private String name;

    public MyRunable(String name){
        this.name = name;
    }

    @Override
    public void run() {

        for(int i = 0;i < 10;i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("正在执行。。。" + name);
        }



    }
}

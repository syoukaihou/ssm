package com.snsprj.test.callback;

public class Employee {

    public void execute(CallBack callBack, String question){

        System.out.println("employee接收到boss的问题");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = "赚个100万";
        callBack.solve(result);
    }
}

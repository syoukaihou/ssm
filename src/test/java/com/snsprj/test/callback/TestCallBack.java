package com.snsprj.test.callback;



public class TestCallBack {


    public static void main(String [] args){

        Employee employee = new Employee();

        Boss boss = new Boss(employee);

        boss.askQuestion("盈利多少？");
    }
}

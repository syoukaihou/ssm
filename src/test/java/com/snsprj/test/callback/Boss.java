package com.snsprj.test.callback;

public class Boss implements CallBack {

    private Employee employee;

    public Boss(Employee employee) {
        this.employee = employee;
    }

    /**
     * 异步回调
     * @param question 问题
     */
    public void askQuestion(final String question){

        System.out.println("boss's question is " + question);

        new Thread(() -> employee.execute(Boss.this, question)).start();

        meeting();
    }

    public void meeting(){
        System.out.println("boss 去开会了");
    }

    @Override
    public void solve(String result) {

        System.out.println("员工给出的答案是：" + result);
    }

}

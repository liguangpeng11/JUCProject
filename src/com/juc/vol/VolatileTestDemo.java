package com.juc.vol;

public class VolatileTestDemo {

    public static void main(String[] args) {

        ThreadDemo threadDemo = new ThreadDemo();

        new Thread(threadDemo).start();
        while (true){
            if(threadDemo.isFlag()){
                System.out.println("main");
                break;
            }
        }

    }

}

class ThreadDemo implements Runnable{

    private boolean flag = false;

    @Override
    public void run() {
        flag = true;
        System.out.println(flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
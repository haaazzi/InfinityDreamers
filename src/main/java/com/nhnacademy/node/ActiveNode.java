package com.nhnacademy.node;

public abstract class ActiveNode extends Node implements Runnable {
    Thread thread;

    ActiveNode() {
        super();
        thread = new Thread(this, getId());
    }

    ActiveNode(String name) {
        this();
        setName(name);
    }

    @Override
    public String getName() {
        return thread.getName();
    }

    @Override
    public void setName(String name) {
        thread.setName(name);
    }

    @Override
    public void run() {

    }

}

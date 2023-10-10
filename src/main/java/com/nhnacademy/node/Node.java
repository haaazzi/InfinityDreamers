package com.nhnacademy.node;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Node {
    public static int count = 0;
    public String id;

    Node() {
        count++;
        this.id = String.format("%s-%02d", getClass().getSimpleName(), count);
        log.info("create node : " + getId());
    }

    public static int getCount() {
        return count;
    }

    public String getId() {
        return id;
    }

    public abstract String getName();

    public abstract void setName(String name);
}
package com.nhnacademy.node;

import java.io.BufferedReader;

public abstract class InputNode extends ActiveNode {
    BufferedReader reader;

    InputNode() {

    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public static void main(String[] args) {

    }
}

package com.nhnacademy.node;

import java.io.BufferedWriter;

public abstract class OutputNode extends ActiveNode {
    BufferedWriter writer;

    OutputNode() {

    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }
}

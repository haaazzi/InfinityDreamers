package com.nhnacademy.node;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class StandardOutNode extends OutputNode {

    public StandardOutNode() {
        super();
        setWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }
}

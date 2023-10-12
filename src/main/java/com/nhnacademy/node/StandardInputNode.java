package com.nhnacademy.node;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StandardInputNode extends InputNode {

    public StandardInputNode() {
        super();
        setReader(new BufferedReader(new InputStreamReader(System.in)));
    }
}

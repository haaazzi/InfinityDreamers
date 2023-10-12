package com.nhnacademy.node;

import com.nhnacademy.Wire;

public class InputOutputNode extends ActiveNode {
    Wire[] inputWires;
    Wire[] outputWires;

    public InputOutputNode(String name, int inputCount, int outputCount) {
        super(name);

        inputWires = new Wire[inputCount];
        outputWires = new Wire[outputCount];
    }

    public InputOutputNode(int inputCount, int outputCount) {
        super();

        inputWires = new Wire[inputCount];
        outputWires = new Wire[outputCount];

    }

    public void connectInputWire(int index, Wire wire) {
        inputWires[index] = wire;
    }

    public void connectOutputWire(int index, Wire wire) {
        outputWires[index] = wire;
    }

    public void output(Request request) {
        for (Wire wire : outputWires) {
            // wire.put(request);
        }
    }
}

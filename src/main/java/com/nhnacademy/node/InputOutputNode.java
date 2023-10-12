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

    public void output(Message message) {
        for (Wire wire : outputWires) {
            wire.put(message);
        }
    }

    public int getInputWireCount() {
        return inputWires.length;
    }

    public int getOutputWireCount() {
        return outputWires.length;
    }

    public Wire getInputWire(int index) {
        return inputWires[index];
    }

    public Wire getOutputWire(int index) {
        return outputWires[index];
    }
}

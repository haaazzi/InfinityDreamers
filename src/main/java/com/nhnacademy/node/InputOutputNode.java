package com.nhnacademy.node;

import java.util.HashMap;
import java.util.Map;

import com.nhnacademy.Wire;
import com.nhnacademy.WireType;

public class InputOutputNode extends ActiveNode {
    HashMap<WireType, Wire> inputWires;
    HashMap<WireType, Wire> outputWires;

    public InputOutputNode(String name) {
        super(name);

        inputWires = new HashMap<>();
        outputWires = new HashMap<>();
    }

    public InputOutputNode() {
        super();

        inputWires = new HashMap<>();
        outputWires = new HashMap<>();

    }

    public void connectInputWire(Wire wire) {
        inputWires.put(wire.getType(), wire);
    }

    public void connectOutputWire(Wire wire) {
        outputWires.put(wire.getType(), wire);
    }

    public void output(Message message, WireType type) {
        for (Map.Entry<WireType, Wire> wire : outputWires.entrySet()) {
            if (wire.getKey().equals(type)) {
                wire.getValue().put(message);
            }
        }
    }

    public int getInputWireCount() {
        return inputWires.size();
    }

    public int getOutputWireCount() {
        return outputWires.size();
    }

    public Wire getInputWire(WireType type) {
        return inputWires.get(type);
    }

    public Wire getOutputWire(WireType type) {
        return outputWires.get(type);
    }
}

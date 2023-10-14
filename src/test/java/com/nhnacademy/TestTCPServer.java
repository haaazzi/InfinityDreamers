package com.nhnacademy;

import com.nhnacademy.node.ContentsParserNode;
import com.nhnacademy.node.HumidityNode;
import com.nhnacademy.node.JsonNode;
import com.nhnacademy.node.TCPServer;
import com.nhnacademy.node.TemperatureNode;
import com.nhnacademy.node.URLParserNode;

public class TestTCPServer {
    public static void main(String[] args) {
        TCPServer server = new TCPServer("TCPServer");
        URLParserNode urlParser = new URLParserNode("PARSER");
        HumidityNode humidity = new HumidityNode("humidity");
        TemperatureNode temperature = new TemperatureNode("temperature");

        ContentsParserNode contentsParser = new ContentsParserNode();
        JsonNode json = new JsonNode();

        Wire wire = new Wire(WireType.PARSER);
        Wire wire2 = new Wire(WireType.HUMIDITY);
        Wire wire3 = new Wire(WireType.PARSER);
        Wire wire4 = new Wire(WireType.JSON);
        Wire wire5 = new Wire(WireType.PARSER);
        Wire wire6 = new Wire(WireType.TEMPERATURE);
        Wire wire7 = new Wire(WireType.PARSER);

        server.connectOutputWire(wire);
        urlParser.connectInputWire(wire);
        urlParser.connectOutputWire(wire2);
        urlParser.connectOutputWire(wire6);
        temperature.connectInputWire(wire6);
        temperature.connectOutputWire(wire7);
        humidity.connectInputWire(wire2);
        humidity.connectOutputWire(wire3);
        contentsParser.connectInputWire(wire3);
        contentsParser.connectInputWire(wire7);
        contentsParser.connectOutputWire(wire4);
        json.connectInputWire(wire4);
        json.connectOutputWire(wire5);
        server.connectInputWire(wire5);

        contentsParser.start();
        json.start();
        humidity.start();
        temperature.start();
        urlParser.start();
        server.start();

    }
}

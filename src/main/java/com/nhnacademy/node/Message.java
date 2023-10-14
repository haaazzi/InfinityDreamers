package com.nhnacademy.node;

public class Message {
    Request request;
    Response response;

    Message(Request request) {
        this.request = request;
        response = new Response();
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

}

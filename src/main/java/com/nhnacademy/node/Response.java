package com.nhnacademy.node;

import org.json.JSONObject;

public class Response {
    JSONObject json;
    String contents;

    Response() {
        this("");
    }

    Response(JSONObject json) {
        this.json = json;
    }

    Response(String contents) {
        this.contents = contents;
    }

    public JSONObject getJson() {
        return json;
    }

    public String getContents() {
        return contents;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}

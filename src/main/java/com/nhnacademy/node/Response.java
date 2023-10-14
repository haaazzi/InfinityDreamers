package com.nhnacademy.node;

import org.json.JSONArray;

public class Response {
    JSONArray json;
    String contents;

    Response() {
        this("");
    }

    Response(JSONArray json) {
        this.json = json;
    }

    Response(String contents) {
        this.contents = contents;
    }

    public JSONArray getJson() {
        return json;
    }

    public String getContents() {
        return contents;
    }

    public void setJson(JSONArray json) {
        this.json = json;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}

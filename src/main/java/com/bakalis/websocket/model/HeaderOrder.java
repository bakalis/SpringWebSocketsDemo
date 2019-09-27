package com.bakalis.websocket.model;

public class HeaderOrder {


    //The name of the sender
    private String name;

    //The header to appear on the website
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    private String header;

    public HeaderOrder() {
    }

    public HeaderOrder(String name, String header) {
        this.name = name;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


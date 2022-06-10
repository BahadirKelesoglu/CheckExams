package com.mobil.odev1;

public class rank {
    private String username,net,code, ID;

    public rank() {
    }

    public rank(String ID, String username, String net, String code) {
        this.ID = ID;
        this.username = username;
        this.net = net;
        this.code = code;
    }

    public String getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

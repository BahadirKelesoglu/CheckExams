package com.mobil.odev1;

public class exams {
    private String Net, Code;
    private String True, False, Total, ID;

    public exams() {
    }

    public exams(String id ,String aTrue,String aFalse, String net, String code, String total) {
        ID = id;
        Net = net;
        Code = code;
        True = aTrue;
        False = aFalse;
        Total = total;
    }

    public String getID() {
        return ID;
    }

    public String getNet() {
        return Net;
    }

    public void setNet(String net) {
        Net = net;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getTrue() {
        return True;
    }

    public void setTrue(String aTrue) {
        True = aTrue;
    }

    public String getFalse() {
        return False;
    }

    public void setFalse(String aFalse) {
        False = aFalse;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}

package com.tnl.lab04_ex5;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class PC {
    private String label;
    private boolean on;

    public void changeMode() {
        this.on = !this.on;
    }

    public PC(int id) {
        this.label = "PC" + String.format("%02d", id);
        this.on = false;
    }

    public PC(String label, boolean on) {
        this.label = label;
        this.on = on;
    }
    public String getLabel() { return label; }

    public void  setLabel(String label) { this.label = label; }

    public boolean isOn() { return on; }

    public void setOn(boolean on) { this.on = on; }

    public static List<PC> generate (int numOfPc) {
        if (numOfPc <= 0 || numOfPc > 100) {
            throw new InvalidParameterException("Num of PC must in 0 - 99");
        }
        List<PC> list = new ArrayList<>();
        for (int i = 1; i <= numOfPc; i++) {
            list.add((new PC(i)));
        }
        return list;
    }

}

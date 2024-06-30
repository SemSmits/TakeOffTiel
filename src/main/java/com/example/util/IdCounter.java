package com.example.util;

import java.io.Serializable;

public class IdCounter implements Serializable {
    private int value;

    public IdCounter(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

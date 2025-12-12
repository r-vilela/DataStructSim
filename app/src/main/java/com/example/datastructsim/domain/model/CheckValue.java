package com.example.datastructsim.domain.model;

import com.example.datastructsim.domain.LinkedListModel;

public class CheckValue implements Action {
    private int expected;

    public CheckValue(int expected) {
        this.expected = expected;
    }

    @Override
    public void run(LinkedListModel model) {
        System.out.println("check");
        //model.lastCheckResult = (model.getCurrentValue() == expected);
        //model.condition = true;
        System.out.println("current value " + model.getCurrentValue() + " Expected " + expected);
    }
}
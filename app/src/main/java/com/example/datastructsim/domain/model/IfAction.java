package com.example.datastructsim.domain.model;

import com.example.datastructsim.domain.LinkedListModel;

import java.util.List;
import java.util.function.BooleanSupplier;

public class IfAction implements Action {
    private int expectedValue;
    private List<Action> thenActions;

    public IfAction(int expectedValue, List<Action> thenActions) {
        this.expectedValue = expectedValue;
        this.thenActions = thenActions;
    }

    @Override
    public void run(LinkedListModel model) {
        int currentValue = model.getCurrentValue();
        System.out.println("if check: current=" + currentValue + " expected=" + expectedValue);

        if (currentValue == expectedValue) {
            for (Action action : thenActions) {
                action.run(model);
            }
        }
    }
}


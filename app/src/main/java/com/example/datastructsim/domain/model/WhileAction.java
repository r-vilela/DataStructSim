package com.example.datastructsim.domain.model;

import com.example.datastructsim.domain.LinkedListModel;

import java.util.ArrayList;
import java.util.List;

public class WhileAction implements Action {
    private List<Action> loopActions;
    private int currentActionIndex = 0;

    public WhileAction(List<Action> loopActions) {
        this.loopActions = loopActions;
    }

    @Override
    public void run(LinkedListModel model) {
        System.out.println("while loop: pointer=" + model.pointer + " size=" + model.getSize());

        if (model.pointer < 0 || model.pointer >= model.getSize()) {
            System.out.println("While loop ended: pointer out of bounds");
            return;
        }

        List<Action> actionsToInsert = new ArrayList<>();
        for (Action action : loopActions) {
            actionsToInsert.add(action);
        }
        actionsToInsert.add(new WhileAction(loopActions));

        for (int i = actionsToInsert.size() - 1; i >= 0; i--) {
            model.insertActionAt(0, actionsToInsert.get(i));
        }
    }
}
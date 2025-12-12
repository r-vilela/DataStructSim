package com.example.datastructsim.domain.model;

import com.example.datastructsim.domain.LinkedListModel;

public class RemoveNode implements Action {
    @Override
    public void run(LinkedListModel model) {
        System.out.println("remove node at pointer: " + model.pointer);
        if (model.pointer >= 0 && model.pointer < model.getSize()) {
            model.removeAtPointer();
        }
    }
}
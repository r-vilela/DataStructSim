package com.example.datastructsim.domain.model;


import com.example.datastructsim.domain.LinkedListModel;
public class MoveNext implements Action {
    @Override
    public void run(LinkedListModel model) {
        System.out.println("move next");
        if (model.pointer < model.getSize() - 1) {
            model.pointer++;
        } else {
            model.pointer = -1; // End of list
        }
    }
}

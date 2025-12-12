package com.example.datastructsim.domain.model;

import com.example.datastructsim.domain.LinkedListModel;

public class StartAtHead implements Action {
    @Override
    public void run(LinkedListModel model) {
        System.out.println("start at head");
        model.pointer = 0;
    }
}
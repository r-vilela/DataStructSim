package com.example.datastructsim.domain;

import com.example.datastructsim.domain.model.Action;
import com.example.datastructsim.domain.model.CheckValue;
import com.example.datastructsim.domain.model.IfAction;
import com.example.datastructsim.domain.model.MoveNext;
import com.example.datastructsim.domain.model.Node;
import com.example.datastructsim.domain.model.RemoveNode;
import com.example.datastructsim.domain.model.StartAtHead;
import com.example.datastructsim.domain.model.WhileAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkedListModel {
    private ArrayList<Node> list;
    private ArrayList<Action> actions = new ArrayList<>();
    private Integer size;
    private String code;
    public int pointer = -1;
    private int idx;

    public LinkedListModel() {
        list = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<5; i++){
            list.add(new Node(random.nextInt(10)));
        }
        size = 5;
        code = "";
    }

    public void addElements(List<Integer> elements) {
        for(int i : elements){
            list.add(new Node(i));
        }
        size = list.size();
    }

    public int getSize(){return this.size;}

    public int getIdx(int idx){
        if (idx >= 0 && idx < list.size())
            return list.get(idx).getValue();
        return -1;
    }

    public int getCurrentValue() {
        if (pointer >= 0 && pointer < list.size())
            return list.get(pointer).getValue();
        return -1;
    }

    public ArrayList<Action> getActions() { return actions; }

    public void setCode(String code){
        this.code = code;
        compile();
    }

    private void compile() {
        actions.clear();
        pointer = -1;
        String[] lines = code.split("\n");
        idx = 0;
        parseBlock(lines, actions);
    }

    private void parseBlock(String[] lines, List<Action> actionList) {
        while (idx < lines.length) {
            String line = lines[idx].trim();

            if (line.isEmpty()) {
                idx++;
                continue;
            }

            if (line.equals("}")) {
                idx++;
                return;
            }

            Action action = parseLine(lines, line);
            if (action != null) {
                actionList.add(action);
            }
            idx++;
        }
    }

    private Action parseLine(String[] lines, String line) {
        if (line.equals("node = head")) {
            return new StartAtHead();
        }
        else if (line.equals("node = node.next")) {
            return new MoveNext();
        }
        else if (line.equals("remove node")) {
            return new RemoveNode();
        }
        else if (line.matches("if node\\.value == \\d+ \\{")) {

            int value = Integer.parseInt(line.replaceAll("[^0-9]", ""));

            List<Action> thenActions = new ArrayList<>();

            idx++;
            parseBlock(lines, thenActions);
            idx--;

            return new IfAction(value, thenActions);
        }
        else if (line.matches("while node != null \\{")) {
            List<Action> loopActions = new ArrayList<>();

            idx++;
            parseBlock(lines, loopActions);
            idx--;

            return new WhileAction(loopActions);
        }

        throw null;
    }

    public void removeAtPointer() {
        if (pointer >= 0 && pointer < list.size()) {
            list.remove(pointer);
            size--;

            if (pointer >= list.size() && list.size() > 0)
                pointer = list.size() - 1;
            else if (list.size() == 0)
                pointer = -1;
        }
    }

    public void insertActionAt(int position, Action action) {
        if (position >= 0 && position <= actions.size()) {
            actions.add(position, action);
        }
    }
}

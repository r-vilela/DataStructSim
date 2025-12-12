package com.example.datastructsim.domain.model;

public class Node {
    int value;
    Node next;
    private int selected;

    public Node(int value){
        this.value = value;
        next = null;
    }
    public Node(){
        this.value = -1;
        next = null;
    }
    
    public int getValue(){return this.value;}
    public Node getNext(){
        if(this.selected == 0)
            return null;
        this.selected--;
        return this.next;
    }
    public int getSelected(){return this.selected;}

    public void setValue(int value){
        this.value = value;
    }
    public void setNext(Node next){
        this.next = next;
    }
    public void toggleSelected(){this.selected++;}



}

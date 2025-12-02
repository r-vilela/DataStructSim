package com.example.datastructsim.domain.model;

public class Node {
    int value;
    Node next;

    public Node(int value){
        this.value = value;
        next = null;
    }
    public Node(){
        this.value = -1;
        next = null;
    }
    
    public int getValue(){return this.value;}
    public Node getNext(){return this.next;}

    public void setValue(int value){
        this.value = value;
    }
    public void setNext(Node next){
        this.next = next;
    }

}

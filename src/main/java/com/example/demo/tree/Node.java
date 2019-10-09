package com.example.demo.tree;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Node {

    public int val;
    public Node leftNode;
    public Node rightNode;

    public Node(){}

    public Node(int val) {
        this.val = val;
    }


}

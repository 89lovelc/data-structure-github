package com.lovelc;

import java.util.Objects;

public class Node {

    public int x;

    public int y;

    public Node pre;


    public Node(int x, int y, Node pre) {
        this.x = x;
        this.y = y;
        this.pre = pre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

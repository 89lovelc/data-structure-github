package com.lovelc;


import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class QueueMaze {


    private static int[][] arr = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };


    public static void print(int[][] arr1) {            //打印地图的方法
        for (int[] ints : arr1) {
            for (int j = 0; j < ints.length; j++) {
                if (ints[j] == 1) {
                    System.out.print("▇" + "\t");
                } else if (ints[j] == 3) {
                    System.out.print("*" + "\t");
                } else {
                    System.out.print("  " + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Node start = new Node(1, 0, null);
        Node end = new Node(18, 19, null);

        Node path = findPath(start, end);

        Node pre = path;
        while (pre != null) {
            arr[pre.x][pre.y] = 3;
            pre = pre.pre;
        }

        print(arr);
    }


    private static Node findPath(Node start, Node end) {

        //进行放入的队列
        Queue<Node> nodes = new LinkedList<>();

        //进入队列 里面的都进行标记 标记的都是为 2
        arr[start.x][start.y] = 2;
        nodes.offer(start);


        while (true) {
            //出队列 判断是否是end节点
            Node poll = nodes.poll();
            //如果是最后 跳出
            if (Objects.equals(poll, end)) {
                return poll;
            }

            //为空就是找不到 end
            if (Objects.isNull(poll)) {
                return null;
            }

            //得到该队列的上下左右的节点进入队列中
            //上
            Node node = existNode(poll.x - 1, poll.y, poll);
            if (Objects.nonNull(node)) {
                nodes.offer(node);
            }

            //下
            node = existNode(poll.x + 1, poll.y, poll);
            if (Objects.nonNull(node)) {
                nodes.offer(node);
            }

            //左
            node = existNode(poll.x, poll.y - 1, poll);
            if (Objects.nonNull(node)) {
                nodes.offer(node);
            }

            //右
            node = existNode(poll.x, poll.y + 1, poll);
            if (Objects.nonNull(node)) {
                nodes.offer(node);
            }

        }

    }

    static private Node existNode(int x, int y, Node node) {

        //判断x y所构成的节点是否合法
        if (x >= 0 && x <= 19
                && y >= 0 && y <= 19
                && arr[x][y] == 0) {
            arr[x][y] = 2;
            return new Node(x, y, node);
        }

        return null;
    }


}

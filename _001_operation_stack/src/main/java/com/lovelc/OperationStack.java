package com.lovelc;

import java.util.Objects;
import java.util.Stack;

public class OperationStack {


    /**
     * 将中缀变成后缀 其中数字将# 给隔开
     *
     * @param string
     * @return
     */
    public static String infix2suffix(String string) {
        Stack<Character> opr = new Stack<Character>();
        StringBuilder stringBuilder = new StringBuilder("");
        char[] chars = string.toCharArray();
        int size = chars.length;
        int index = 0;
        while (index < size) {


            switch (chars[index]) {
                case '(':
                    opr.push(chars[index]);
                    break;
                case ')':
                    while (opr.peek() != '(') {
                        stringBuilder.append(opr.pop()).append("#");
                    }
                    opr.pop();
                    break;
                case '+':
                case '-':
                    while (!opr.isEmpty() && opr.peek() != '(') {
                        stringBuilder.append(opr.pop()).append("#");
                    }
                    opr.push(chars[index]);
                    break;
                case '*':
                case '/':
                    if (opr.isEmpty()) {
                        opr.push(chars[index]);
                        break;
                    }

                    while (opr.peek() == '*' || opr.peek() == '/') {
                        stringBuilder.append(opr.pop()).append("#");
                    }
                    opr.push(chars[index]);
                    break;
                default:
                    //成为数字的时候
                    int num = chars[index] - '0';
                    while ((index + 1) < size && chars[index + 1] >= '0' && chars[index + 1] <= '9') {
                        num = num * 10 + chars[index + 1] - '0';
                        index++;
                    }
                    stringBuilder.append(num).append("#");

                    break;

            }

            index++;


        }

        while (!opr.isEmpty()) {
            stringBuilder.append(opr.pop()).append("#");
        }

        return stringBuilder.toString();
    }


    /**
     * 将后缀表达式进行计算
     *
     * @param string
     * @return
     */
    public static int calculateSuffix(String string) {
        String[] items = string.split("#");
        Stack<Integer> stack = new Stack<Integer>();

        for (String item : items) {
            if ("+-*/".contains(item)) {
                int num_2 = stack.pop();
                int num_1 = stack.pop();

                stack.push(calculateOpr(num_1, item, num_2));
                continue;
            }
            stack.push(Integer.parseInt(item));
        }


        return stack.pop();
    }

    private static Integer calculateOpr(int num_1, String item, int num_2) {
        if (Objects.equals(item, "+")) {
            return num_1 + num_2;
        }

        if (Objects.equals(item, "-")) {
            return num_1 - num_2;
        }

        if (Objects.equals(item, "*")) {
            return num_1 * num_2;
        }

        if (Objects.equals(item, "/")) {
            return num_1 / num_2;
        }

        return 0;
    }


    public static Integer calculate(String string) {
        System.out.println("得到的式子为" + string);
        String suffix = infix2suffix(string);
        System.out.println("得到后缀式子为" + suffix);
        int result = calculateSuffix(suffix);
        System.out.println("最后结果为" + result);

        return result;
    }


    public static void main(String[] args) {
        calculate("1+2+3");
        System.out.println();


        calculate("2*3-1");
        System.out.println();


        calculate("(2+10)/(3-1)+4*1");
        System.out.println();

    }

}

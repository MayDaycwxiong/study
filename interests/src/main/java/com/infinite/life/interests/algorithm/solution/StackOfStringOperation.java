package com.infinite.life.interests.algorithm.solution;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;
/**
 * @description     计算 String s="3+6*2-5*(9/(5*(4-2)-7)+(5/2-1))+8" 的值
 * @author cuiwx
 * @versoin 1.0  2019/8/31
 */
@Slf4j
public class StackOfStringOperation {

    /**
     * 遍历字符串，数字进栈，操作符进栈
     * 此处未作相应的字符串检测规则，假设都是0-9的数值，表达式正确
     *
     * @param needDeal 待处理字符串
     * @return 字符串计算结果
     */
    public static int deal(String needDeal) {
        Stack<Integer> value = new Stack<>();
        Stack<Character> operation = new Stack<>();
        for (int i = 0; i < needDeal.length(); i++) {
            switch (needDeal.charAt(i)) {
                case '(':
                    operation.push(needDeal.charAt(i));
                    break;
                case ')':
                    operation.push(needDeal.charAt(i));
                    calculate(value, operation);
                    break;
                case '+':
                    compareAndCalculate(operation, needDeal.charAt(i), value);
                    break;
                case '-':
                    compareAndCalculate(operation, needDeal.charAt(i), value);
                    break;
                case '*':
                    compareAndCalculate(operation, needDeal.charAt(i), value);
                    break;
                case '/':
                    compareAndCalculate(operation, needDeal.charAt(i), value);
                    break;
                default:
                    value.push(Integer.parseInt(needDeal.charAt(i) + ""));
                    break;
            }
        }
        // 字符串遍历完毕后，操作符栈不为空，则一直进行计算
        while (operation.size() > 0) {
            calculate(value, operation);
        }
        return value.pop();
    }

    /**
     * 比较操作符优先级，优先级高则进栈，反之计算值
     *
     * @param operation
     * @param charAt
     * @param value
     */
    private static void compareAndCalculate(Stack<Character> operation, char charAt, Stack<Integer> value) {
        if (operation.size() < 1 || rank(operation.peek()) < rank(charAt)) {
            operation.push(charAt);
        } else {
            // 栈中优先级高的进行计算
            while (operation.size() > 0 && rank(charAt) <= rank(operation.peek())) {
                calculate(value, operation);
            }
            operation.push(charAt);
        }
    }

    /**
     * 计算操作符优先级
     *
     * @param charAt 操作符
     * @return
     */
    private static int rank(char charAt) {
        int rank = 0;
        switch (charAt) {
            case ')':
                rank = 4;
                break;
            case '*':
                rank = 3;
                break;
            case '/':
                rank = 3;
                break;
            case '+':
                rank = 2;
                break;
            case '-':
                rank = 2;
                break;
            case '(':
                rank = 1;
                break;
            default:
                break;
        }
        return rank;
    }

    /**
     * 弹出数值进行计算
     *
     * @param value
     * @param operation
     */
    private static void calculate(Stack<Integer> value, Stack<Character> operation) {
        // 遇到')'则一直计算直到遇到'('
        if (operation.peek() == ')') {
            operation.pop();
            while (operation.peek() != '(') {
                calculate(value, operation);
            }
            operation.pop();
            return;
        }
        int result = 0;
        int second = value.pop();
        int first = value.pop();
        switch (operation.pop()) {
            case '+':
                result = first + second;
                break;
            case '-':
                result = first - second;
                break;
            case '*':
                result = first * second;
                break;
            case '/':
                result = first / second;
                break;
            default:
                return;
        }
        value.push(result);
    }
}

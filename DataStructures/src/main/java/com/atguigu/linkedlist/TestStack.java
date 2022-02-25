package com.atguigu.linkedlist;

import com.atguigu.stack.ReversePolishMultiCalc;

import java.util.Stack;

//演示栈Stack的基本使用
public class TestStack {

    public static void main(String[] args) {
//        Stack<String> stack = new Stack<String>();
//        //入栈
//        stack.add("jack");
//        stack.add("tom");
//        stack.add("smith");
//
//        //出栈
//        while (stack.size()>0){
//            System.out.println(stack.pop()); //pop就是将栈顶的数据取出
//        }

        String math = "9+(3-1)*3+10/2";
        try {
            Double d = ReversePolishMultiCalc.doCalc(ReversePolishMultiCalc.doMatch(math));
            System.out.println(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

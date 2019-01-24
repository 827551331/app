package com.ws.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    //递归方式
    private static int fibonaccRecursion(int n) {

        if (n <= 1) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        return fibonaccRecursion(n - 1) + fibonaccRecursion(n - 2);
    }

    //循环方式求解
    private static int fibonaccLoop(int n) {

        if (n <= 1) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }

        //因为斐波那契数列是从0和1开始并在第三个数的时候才开始有规律
        int result = 0, a1 = 0, a2 = 1;
        for (int i = 3; i <= n; i++) {
            result = a1 + a2;
            a1 = a2;
            a2 = result;
        }
        return result;
    }

    public static int getFib(int n){
        if(n < 0){
            return -1;
        }else if(n == 0){
            return 0;
        }else if(n == 1 || n ==2){
            return 1;
        }else{
            return getFib(n - 1) + getFib(n - 2);
        }
    }

    public static void main(String[] args) {
        int n = 32;

        System.out.println(Solution.getFib(32));
    }
}

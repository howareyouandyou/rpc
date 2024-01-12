package com.yyl.rpc.example;

/**
 * @author 86152
 * @version 1.0
 * Create by 2024/1/12 16:41
 */
public class CalcServiceImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}

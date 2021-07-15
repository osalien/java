package com.osalien.java.extend;

import java.util.ArrayList;
import java.util.List;

public class Demo_Polymorphism_02 extends Demo_Polymorphism_01{
    public Demo_Polymorphism_02(){
        System.out.println("B");
    }

    public static void main(String[] args) {
        Demo_Polymorphism_01 ab = new Demo_Polymorphism_02();
        ab = new Demo_Polymorphism_01();

    }
}

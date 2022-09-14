package com.gordon;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        int loop = 0;
//        while (loop++ < 5) {
//            System.out.println("loop=" + loop);
//        }
//        HashMap<String,String> map = new HashMap<String,String>();
//        Class<? extends HashMap> mapClass = map.getClass();
        System.out.println("child isAssignable parent:"+(Student.class.isAssignableFrom(Person.class)));
        System.out.println("parent isAssignable from:"+(Person.class.isAssignableFrom(Student.class)));
    }

    class Person {
        public String name;
    }

    class Student extends Person {

    }
}
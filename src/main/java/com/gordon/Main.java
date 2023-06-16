package com.gordon;

import com.gordon.utils.Student;
import com.gordon.utils.StudentUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Student s1 = StudentUtils.getStudent();
        Student s2 = StudentUtils.getStudent();
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);

        List<String> list1 = new ArrayList<String>();
        List<? extends Object> list2 = new ArrayList<>();
//        list2.add(s1);
//        list2.add(s2);
//        list2.add(s1);
//        list2.add("ssss");
        list2 = list1;
    }

}
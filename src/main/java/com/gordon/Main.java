package com.gordon;

import com.gordon.utils.Student;
import com.gordon.utils.StudentUtils;

public class Main {
    public static void main(String[] args) {

        Student s1 = StudentUtils.getStudent();
        Student s2 = StudentUtils.getStudent();
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
    }

}
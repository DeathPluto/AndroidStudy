package com.halcyon.model;

import java.util.List;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class Student {
    public String name;
    public String id;
    public List<Course> courses;

    public Student() {
    }

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Student(String name, String id, List<Course> courses) {
        this.name = name;
        this.id = id;
        this.courses = courses;
    }

    public static class Course{
        public String name;

        public Course(String name) {
            this.name = name;
        }
    }
}

package com.example.w24comp1008lhw9;

import java.util.ArrayList;

public class Experiment {
    public static void main(String[] args) {

        ArrayList<Course> courses = DBUtility.getCoursesFromDB();
        for (Course course : courses){
            System.out.printf("%d %s %s%n", course.getCrn(),course.getCourseCode(),course.getCourseName());
        }

    }

}

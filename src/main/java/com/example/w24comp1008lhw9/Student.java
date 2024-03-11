package com.example.w24comp1008lhw9;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person{

    private int studentNum;
    private ArrayList<Grade> grades;

    public Student(String firstName, String lastName, String address, LocalDate birthday, int studentNum, ArrayList<Grade> grades) {
        super(firstName, lastName, address, birthday);
        setStudentNum(studentNum);
        this.grades = grades;
    }

    /**
     * This constructor is said to be "Over Loaded" meanning there are 2 constructors,
     * but they have different arguments.
     */
    public Student(String firstName, String lastName, String address, LocalDate birthday, int studentNum) {
        super(firstName, lastName, address, birthday);
        setStudentNum(studentNum);
        this.grades = new ArrayList<>();
    }


    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        if (studentNum>= 100000000 && studentNum <= 999999999)
            this.studentNum = studentNum;
        else
            throw new IllegalArgumentException("Student number must be a 9 digit number");
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public String toString(){
        return String.format("%s, student number: %d",super.toString(),studentNum,getAvgGrade());
    }

    public double getAvgGrade()
    {
        if (grades.size()==0)
            return -1;

        double total = 0;
        for (Grade grade : grades)
            total += grade.getGrade;

        return total/grades.size();
    }



}

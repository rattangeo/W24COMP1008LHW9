package com.example.w24comp1008lhw9;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBUtility {
    private static String user ="root";
    private static String password ="rootroot";

    //jdbc:mysql - this is the driver we are using
    //localhost - tells java the MySQL server address is mysql and port is 3306
    //edmuse2024 - this is the database name
    private static String connectUrl = "jdbc:mysql://localhost:3306/edmuse2024";

    //To connect to mySQL server
    //1. update pom.xml file with the dependency
    //2. update module.info file with java.sql
    //3. We need the connection defined above


    public static ArrayList<Course> getCoursesFromDB(){
        ArrayList<Course> courses = new ArrayList<>();

        try (
            Connection conn = DriverManager.getConnection(connectUrl,user,password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");
        ){
            //loop over the resultset and create course objects
            while (resultSet.next()){
                int crn = resultSet.getInt("crn");
                String courseCode = resultSet.getString("courseCode");
                String courseName = resultSet.getString("courseName");

                courses.add(new Course(crn,courseCode,courseName));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return courses;
    }

    public static ArrayList<Student> getStudentsFromDB(){

        ArrayList<Student> students = new ArrayList<>();
        try(
                Connection conn = DriverManager.getConnection(connectUrl,user,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
                ) {
            while (resultSet.next()){
                int studentNum = resultSet.getInt("studentNum");
                ArrayList<Grade> grades = getGradesFromDB(studentNum);
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();

                students.add(new Student(firstName,lastName,address,birthday,studentNum,grades));

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

//    private static ArrayList<Grade> getGradesFromDB(int studentNum){
//        ArrayList<Grade> grades = new ArrayList<>();
//        try (
//                Connection conn = DriverManager.getConnection(connectUrl,user,password);
//                Statement statement = conn.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM grades WHERE studentNum = " + studentNum);
//
//        )
//        {
//            while (resultSet.next()){
//
//                int crn = resultSet.getInt("crn");
//                int grade = resultSet.getInt("grade");
//                grades.add(new Grade(studentNum,crn,grade));
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return grades;
//    }

    //Using prepared statements

    private static ArrayList<Grade> getGradesFromDB(int studentNum){
        ArrayList<Grade> grades = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(connectUrl,user,password);
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM grades WHERE studentNum = ?");
                ){
            preparedStatement.setInt(1,studentNum); // Setting the value for the parameter

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int crn = resultSet.getInt("crn");
                    int grade = resultSet.getInt("grade");

                    grades.add(new Grade(studentNum,crn,grade));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return grades;
    }





}

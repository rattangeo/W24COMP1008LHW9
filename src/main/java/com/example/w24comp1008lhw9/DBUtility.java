package com.example.w24comp1008lhw9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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


}

package com.jaedaraz.soapws.service;

import com.jaedaraz.soapws.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseService {
    private static List<Course> courses = new ArrayList<>();

    static{
        Course course1 = new Course(1, "firstCourse", "very fun course 1");
        Course course2 = new Course(2, "secondCourse", "very fun course 2");
        Course course3 = new Course(3, "thirdCourse", "very fun course 3");

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
    }
    
    public Course getCourseById(int id){
        for (Course course: courses) {
            if(course.getId() == id){
                return course;
            }
        }
        
        return null;
    }

    public List<Course> findAll(){
        return courses;
    }

    public void deleteById(int id){
        courses.removeIf(course -> course.getId() == id);
    }
}

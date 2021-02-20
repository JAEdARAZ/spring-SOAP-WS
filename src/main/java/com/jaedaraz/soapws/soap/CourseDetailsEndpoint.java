package com.jaedaraz.soapws.soap;

import com.jaedaraz.soapws.bean.Course;
import com.jaedaraz.soapws.entity.*;
import com.jaedaraz.soapws.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    private CourseService courseService;

    //get Course by id
    @PayloadRoot(namespace = "http://github.com/JAEdARAZ/spring-SOAP-WS", localPart = "GetCourseDetailsRequest")
    @ResponsePayload //from Java to XML
    public GetCourseDetailsResponse processCourseDetailsRequest (@RequestPayload GetCourseDetailsRequest request){ //@RequestPayload: from XML to Java
        Course course = courseService.getCourseById(request.getId());
        if(course == null){
            throw new CourseNotFoundException("the Course id is invalid - " + request.getId());
        }

        return this.mapCourseDetail(course);
    }

    //get All courses
    @PayloadRoot(namespace = "http://github.com/JAEdARAZ/spring-SOAP-WS", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload //from Java to XML
    public GetAllCourseDetailsResponse processCourseDetailsRequest (@RequestPayload GetAllCourseDetailsRequest request){ //@RequestPayload: from XML to Java
        return this.mapAllCourseDetail(courseService.findAll());
    }

    //delete Course by id
    @PayloadRoot(namespace = "http://github.com/JAEdARAZ/spring-SOAP-WS", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload //from Java to XML
    public DeleteCourseDetailsResponse processDeleteCourseDetailsRequest (@RequestPayload DeleteCourseDetailsRequest request){ //@RequestPayload: from XML to Java
        CourseService.Status status = courseService.deleteById(request.getId());
        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(this.mapStatus(status));
        return response;
    }

    private GetCourseDetailsResponse mapCourseDetail(Course course){
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    private GetAllCourseDetailsResponse mapAllCourseDetail(List<Course> courses){
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for (Course course : courses) {
            CourseDetails details = this.mapCourse(course);
            response.getCourseDetails().add(details);
        }

        return response;
    }

    private CourseDetails mapCourse(Course course){
        CourseDetails details = new CourseDetails();

        details.setId(course.getId());
        details.setName(course.getName());
        details.setDescription(course.getDescription());

        return details;
    }

    private Status mapStatus(CourseService.Status status){
        if(status == CourseService.Status.FAILURE){
            return Status.FAILURE;
        }

        return Status.SUCCESS;
    }
}

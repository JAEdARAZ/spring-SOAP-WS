package com.jaedaraz.soapws.soap;

import com.jaedaraz.soapws.entity.CourseDetails;
import com.jaedaraz.soapws.entity.GetCourseDetailsRequest;
import com.jaedaraz.soapws.entity.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    //input - GetCourseRequest
    //output - GetCourseResponse
    @PayloadRoot(namespace = "http://github.com/JAEdARAZ/spring-SOAP-WS", localPart = "GetCourseDetailsRequest")
    @ResponsePayload //from Java to XML
    public GetCourseDetailsResponse processCourseDetailsRequest (@RequestPayload GetCourseDetailsRequest request){ //@RequestPayload: from XML to Java
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails details = new CourseDetails();

        details.setId(request.getId());
        details.setName("Spring SOAP WS course");
        details.setDescription("Very interesting course");

        response.setCourseDetails(details);
        return response;
    }

}

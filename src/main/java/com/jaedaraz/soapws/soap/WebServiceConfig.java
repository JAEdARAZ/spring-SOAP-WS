package com.jaedaraz.soapws.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
    //MessageDispatcherServlet: manages requests and identifies which endpoint handles it

    @Bean //map servlet to a URI
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    @Bean(name = "courses") //autowires the schema (xsdschema becomes a dependency)
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CoursePort"); // CoursePort, smth related to wsdl
        definition.setTargetNamespace("http://github.com/JAEdARAZ/spring-SOAP-WS");
        definition.setLocationUri("/ws");
        definition.setSchema(coursesSchema);

        return definition;
    }

    @Bean
    public XsdSchema coursesSchema(){ //this is the schema we will use in the wsdl definition
        return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
    }
}

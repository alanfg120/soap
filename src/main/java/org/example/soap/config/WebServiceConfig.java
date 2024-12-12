package org.example.soap.config;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.example.soap.interceptor.JwtTokenInterceptor;
import org.example.soap.services.Auth;
import org.example.soap.services.FileRead;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {
    @Bean
    public ServletRegistrationBean<CXFServlet> cxfServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/ws/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new FileRead());
        endpoint.getInInterceptors().add(new JwtTokenInterceptor());
        endpoint.publish("/Files");
        return endpoint;
    }

    @Bean
    public Endpoint endpointAuth() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new Auth());
        endpoint.publish("/Auth");
        return endpoint;
    }

}

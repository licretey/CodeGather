package com.sourceLearn.a08;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Date: 2024/8/19 15:25
 */
@RestController
public class MyController {
    @Lazy
    @Autowired
    private Bean4Request beanForRequest;

    @Lazy
    @Autowired
    private Bean4Session beanForSession;

    @Lazy
    @Autowired
    private Bean4Application beanForApplication;

    @GetMapping(value = "/test", produces = "text/html")
    public String test(HttpServletRequest request, HttpSession session){
        ServletContext sc = request.getServletContext();
        String sb = "<ul>"+
                "<li>Request scope: "+beanForRequest+"</li>"+
                "<li>Session scope: "+beanForSession+"</li>"+
                "<li>Application scope: "+beanForApplication+"</li>"+
                "</ul>";
        return sb;
    }

}

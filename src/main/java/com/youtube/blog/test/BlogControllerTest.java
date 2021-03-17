package com.youtube.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Spring이 com.youtube.blog 패키지 이하를 스캔해서 모든 파일을 매모리에 new하는건 아니며
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서(IoC) 스프링 컨테이너에 관리해준다.
@RestController
public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String hello() {

        return "<h1>Hello Spring Boot</h1>";
    }
}


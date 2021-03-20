package com.youtube.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@Controller가 붙은 어노테이션은 메서드는 파일을 리턴함
//파일리턴 기본경로 : src/main/resources/static 이 경로에 있는 파일을 리턴해라~
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        //return "home.html"; 이렇게 하면 안됨 경로가 src/main/resources/statichome.html 이런식이 됨
        return "/home.html";
    }

}

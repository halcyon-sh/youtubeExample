package com.youtube.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 > html 파일로 응답
//@Controller

//사용자가 요청 > data 파일로 응답(string)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";

    @GetMapping("/http/lombok")
    public String lombokTest() {

        Member m = Member.builder().username("halcyon").password("1234").email("symanito@naver.com").build();
                  //@Build 때문에 표현가능, 순서필요없음, 모든항목 채우지 않아도 됨됨                  //new Member(1, "halcyon","1234", "sy_manito@naver.com" );
        System.out.println(TAG + "getter : " + m.getUsername());
        m.setUsername("TEST");
        System.out.println(TAG + "setter : " + m.getUsername());

        return  "LombokTest 완료";
    }

    //http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id,
//                          @RequestParam String username) {
    public String getTest(Member member) {
        return "get 요청 : " + member.getId() + "," + member.getUsername() + "," + member.getPassword() + "," + member.getEmail();
    }

    //인터넷브라우저 요청은 get만 가능
    //http://localhost:8080/http/post (insert)
    // 데이터를 받을때는 @RequestBody를 사용해야지만 가져옴
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member member) { // MessageConverter(스프링부트)
        return "post 요청 : " + member.getId() + "," + member.getUsername() + "," + member.getPassword() + "," + member.getEmail();
    }

    //http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member member) {
        return "put 요청 : " + member.getId() + "," + member.getUsername() + "," + member.getPassword() + "," + member.getEmail();
    }

    //http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}

package com.youtube.blog.test;

import com.youtube.blog.model.User;
import com.youtube.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired // --> DummyControllerTest가 메모리에 올라갈 때 아래꺼도 같이 올라감 // 의존성 주입 -> DI
    private UserRepository userRepository;

    //http://localhost:8000/blog/dummy/join(요청)
    //http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    /*
    public String join(String username, String password, String email) { //key=value(약속된 규칙)
        System.out.println("username : " + username);
        System.out.println("password : " + password);
        System.out.println("email : " + email);
        return "회원가입이 완료되었습니다.";
    }*/
    public String join(User user) { //더 강력하게 object를 받아올 수 있음
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        userRepository.save(user); //DI한 UserRepository가 JpaRepository를 extends 하고 있으니까 save도 먹음 -> 테이블 insert

        return "회원가입이 완료되었습니다.";
    }
}

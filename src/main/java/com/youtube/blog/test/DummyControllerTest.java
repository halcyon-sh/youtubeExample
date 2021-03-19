package com.youtube.blog.test;

import com.youtube.blog.model.RoleType;
import com.youtube.blog.model.User;
import com.youtube.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController //html 파일이 아니라 data를 리턴해주는 controller
public class DummyControllerTest {

    @Autowired // --> DummyControllerTest가 메모리에 올라갈 때 아래꺼도 같이 올라감 // 의존성 주입 -> DI
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한페이지 당 2건의 데이터를 리턴받아 볼 예정 //스프링부트 jpa의 강력
    @GetMapping("/dummy/user")
/*    public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }
*/  // List 형태로 생성
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    //{id} 주소로 파라미터를 전달받을 수 있음
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // findById의 리턴형이 Optional 형임
        // 만약 DB에 없는 데이터를 요청하면 NULL이 될껀데 그럼 프로그램상 문제가 될 수 있으니
        // OPTIONAL로 USER객체를 감싸서 가져올테니 NULL인지 판단해서 RETURN하라는 명시를 함함
 /*       User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User(); // 빈객체 리턴 -- new Suppier라는 인터페이스를 사용, 객체가 없으면 빈 객체를 넣어줌(null이이 아님)
           }
        });
        return user;
        좀 더 아래 내용으로 변경
  */
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. ID : " + id);
            }
        });
        //요청 : web 브라우저
        //user 객채 = 자바 오브젝트
        //변환(웹브라우저가 이해할 수 있는 데이터) --> json이 최강임 -> gson 라이브러리를 통해 변환해야 했으나 스프링부트는 자동
        //스프링부트 = MessageConverter 라는 애가 응답시에 자동 작동
        //만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        //User 오브젝트를 json으로 변환해서 브라우저에 던져줌
        return user;

        //람다식으로도 Supplier를 표현 가능
        //User user = userRepository.findById(id).orElseThrow(()-> {
        //    return new IllegalArgumentException("해당사용자는 없습니다.");
        //});
    }

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

        user.setRole(RoleType.USER); //Enum을 정의하여, 고정값을 세팅할 수 있음. 실수방지

        userRepository.save(user); //DI한 UserRepository가 JpaRepository를 extends 하고 있으니까 save도 먹음 -> 테이블 insert

        return "회원가입이 완료되었습니다.";
    }
}

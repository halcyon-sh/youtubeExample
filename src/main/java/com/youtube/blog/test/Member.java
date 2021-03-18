package com.youtube.blog.test;

import lombok.*;

//@Getter
//@Setter
//@RequiredArgsConstructor
@Data //@Data가 @Getter & @Setter 집합임
@NoArgsConstructor
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;
/*
    //생성자 -> lombok의 @RequiredAgrsConstructor 를 사용하면 final 변수/@NonNull인 변수의 생성자를 만들어줌
                         @NoArgsConstructor는 파라미터가 없는 기본생성자
                         @AllArgsConstructor는 모든 필드값을 파라미터로 받는 생성자*/
    @Builder //어노테이션 하나로 builder 패턴 완성
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }


}

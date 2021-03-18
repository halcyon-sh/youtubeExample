package com.youtube.blog.repository;

import com.youtube.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {
                                     // 해당 JpaRepository는 User테이블에 관여, User 테이블의 PK의 데이터는 Integer형이다.
                                     // JpaRepository는 기본적으로 CRUD를 가지고 있음
                                     // JSP로는 DAO임, 자동으로 컴포넌트 스캔하면서 BEAN으로 등록이 된다. -> @Repository 생략가능

}

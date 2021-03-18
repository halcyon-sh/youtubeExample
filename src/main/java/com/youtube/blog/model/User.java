package com.youtube.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class User {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db의 넘버링 전략을 따라간다.
                                                        // Oracle이면 Sequence,  MySQL이면 auto-increment
    private int id; //시퀀스

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; //원래 Enum을 쓰는게 좋음 //admin, user, manager

    @CreationTimestamp //시간 자동입력
    private Timestamp createDate;
}

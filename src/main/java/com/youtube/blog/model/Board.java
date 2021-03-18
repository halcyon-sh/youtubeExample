package com.youtube.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; // 섬머노트 라이브러리 사용예정 <html> 태그가 섞여 디자인 됨

    @ColumnDefault("0")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER /*반드시 필요하니 조인헤서 가져옴*/) // Many = Board, User = One - 한명의 user가 여러 글을 쓸 수 있음
    @JoinColumn(name="userId") //컬럼명 정의
    private User user; //DB는 오브젝트를 저장할수 없음. FK로 매핑함, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다(난 FK가 아니다.) DB에 컬럼을 만들지 말라.
                                   //FK는 reply 테이블의 board 변수를 mappedBy에 매핑
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}

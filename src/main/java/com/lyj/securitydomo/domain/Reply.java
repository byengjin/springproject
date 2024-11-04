package com.lyj.securitydomo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(name = "postId", nullable = false)
    private Long postId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String content;

    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")  // 부모 댓글 ID
    private Reply parent;  // 대댓글일 경우 부모 댓글을 참조

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Reply> children = new ArrayList<>();  // 대댓글 목록

}

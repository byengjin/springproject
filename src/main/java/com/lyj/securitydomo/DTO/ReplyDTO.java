package com.lyj.securitydomo.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReplyDTO {
    private Long replyId;
    private Long postId;
    private Long userId;
    private String content;
    private LocalDateTime regDate;
    private Long parentId;  // 부모 댓글 ID
}
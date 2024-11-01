package com.lyj.securitydomo.service;

import com.lyj.securitydomo.DTO.ReplyDTO;
import com.lyj.securitydomo.domain.Reply;

import java.util.List;

public interface ReplyService {
    Reply saveReply(ReplyDTO replyDTO);
    Reply modifyReply(Long replyId, String content);
    void removeReply(Long replyId);
    List<Reply> getRepliesByPostId(Long postId);

}

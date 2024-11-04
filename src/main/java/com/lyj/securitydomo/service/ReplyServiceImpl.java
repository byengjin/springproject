package com.lyj.securitydomo.service;

import com.lyj.securitydomo.DTO.ReplyDTO;
import com.lyj.securitydomo.domain.Reply;
import com.lyj.securitydomo.repository.ReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    public ReplyServiceImpl(ReplyRepository replyRepository, ModelMapper modelMapper) {
        this.replyRepository = replyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Reply saveReply(ReplyDTO replyDTO) {
        // ReplyDTO를 Reply 엔티티로 변환
        Reply reply = modelMapper.map(replyDTO, Reply.class);

        // 대댓글일 경우, 부모 댓글 설정
        if (replyDTO.getParentId() != null) {
            Reply parentReply = replyRepository.findById(replyDTO.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent reply not found"));
            reply.setParent(parentReply);  // 부모 댓글 설정
        }

        // 댓글 저장
        return replyRepository.save(reply);
    }

    @Override
    public Reply modifyReply(Long replyId, String content) {
        Reply reply = replyRepository.findById(replyId).orElseThrow();
        reply.setContent(content);
        return replyRepository.save(reply);
    }

    @Override
    public void removeReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    @Override
    public List<Reply> getRepliesByPostId(Long postId) {
        return replyRepository.findByPostId(postId);
    }


}

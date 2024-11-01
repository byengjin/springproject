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
        this.modelMapper = modelMapper; // ModelMapper 주입
    }

    @Override
    public Reply saveReply(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class); // 수정
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

package com.lyj.securitydomo.service;

import com.lyj.securitydomo.DTO.ReplyDTO;
import com.lyj.securitydomo.domain.Reply;
import com.lyj.securitydomo.repository.ReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Reply saveReply(ReplyDTO replyDTO) {
        // ReplyDTO를 Reply 엔티티로 변환
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        // Reply 엔티티 저장
        return replyRepository.save(reply); // 저장된 Reply 객체 반환
    }

    @Override
    public Reply modifyReply(Long replyId, String content) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("Reply not found")); // 존재하지 않을 경우 예외 처리
        reply.setContent(content); // 댓글 내용 수정
        return replyRepository.save(reply); // 수정된 댓글 반환
    }

    @Override
    public void removeReply(Long replyId) {
        replyRepository.deleteById(replyId); // 댓글 삭제
    }

    @Override
    public List<Reply> getRepliesByPostId(Long postId) {
        return replyRepository.findByPostId(postId); // 해당 게시글의 댓글 목록 반환
    }
}

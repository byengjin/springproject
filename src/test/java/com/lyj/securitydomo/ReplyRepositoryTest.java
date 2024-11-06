package com.lyj.securitydomo;

import com.lyj.securitydomo.domain.Reply;
import com.lyj.securitydomo.repository.ReplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @DisplayName("댓글 저장 테스트")
    void saveReplyTest() {
        // 댓글 객체 생성
        Reply reply = new Reply();
        reply.setPostId(1L); // 예시 게시글 ID
        reply.setUserId(1L); // 예시 사용자 ID
        reply.setContent("댓글 내용");

        // 댓글 저장
        Reply savedReply = replyRepository.save(reply);

        // 저장된 댓글이 DB에 잘 저장되었는지 확인
        assertNotNull(savedReply.getReplyId()); // ID가 자동 생성되었는지 확인
        assertEquals(reply.getContent(), savedReply.getContent()); // 내용이 같아야 함
    }

    @Test
    @DisplayName("댓글 조회 테스트")
    void findReplyTest() {
        // 댓글 저장
        Reply reply = new Reply();
        reply.setPostId(1L);
        reply.setUserId(1L);
        reply.setContent("조회 테스트 댓글");
        Reply savedReply = replyRepository.save(reply);

        // 댓글 조회
        Reply foundReply = replyRepository.findById(savedReply.getReplyId()).orElse(null);

        // 조회된 댓글이 원본 댓글과 일치하는지 확인
        assertNotNull(foundReply);
        assertEquals(savedReply.getContent(), foundReply.getContent());
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateReplyTest() {
        // 댓글 저장
        Reply reply = new Reply();
        reply.setPostId(1L);
        reply.setUserId(1L);
        reply.setContent("수정 전 댓글");
        Reply savedReply = replyRepository.save(reply);

        // 댓글 수정
        savedReply.setContent("수정된 댓글 내용");
        Reply updatedReply = replyRepository.save(savedReply);

        // 수정된 댓글 내용 확인
        assertEquals("수정된 댓글 내용", updatedReply.getContent());
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteReplyTest() {
        // 댓글 저장
        Reply reply = new Reply();
        reply.setPostId(1L);
        reply.setUserId(1L);
        reply.setContent("삭제 테스트 댓글");
        Reply savedReply = replyRepository.save(reply);

        // 댓글 삭제
        replyRepository.delete(savedReply);

        // 삭제된 댓글 조회 시 null이 반환되는지 확인
        Reply deletedReply = replyRepository.findById(savedReply.getReplyId()).orElse(null);
        assertNull(deletedReply); // 삭제된 댓글은 null이어야 함
    }
}

package com.lyj.securitydomo;

import com.lyj.securitydomo.DTO.ReplyDTO;
import com.lyj.securitydomo.domain.Reply;
import com.lyj.securitydomo.repository.ReplyRepository;
import com.lyj.securitydomo.service.ReplyService;
import com.lyj.securitydomo.service.ReplyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReplyTest {

    @Mock
    private ReplyRepository replyRepository;

    @Mock
    private ModelMapper modelMapper; // ModelMapper를 모의 객체로 추가

    @InjectMocks
    private ReplyServiceImpl replyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveReply() {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setPostId(1L);
        replyDTO.setUserId(1L);
        replyDTO.setContent("Test Reply");

        Reply reply = new Reply();
        reply.setReplyId(1L);
        reply.setPostId(replyDTO.getPostId());
        reply.setUserId(replyDTO.getUserId());
        reply.setContent(replyDTO.getContent());
        reply.setRegDate(LocalDateTime.now());

        when(modelMapper.map(replyDTO, Reply.class)).thenReturn(reply); // ModelMapper의 동작 정의
        when(replyRepository.save(any(Reply.class))).thenReturn(reply);

        Reply savedReply = replyService.saveReply(replyDTO);

        assertNotNull(savedReply);
        assertEquals("Test Reply", savedReply.getContent());
        verify(replyRepository, times(1)).save(any(Reply.class));
    }

    @Test
    void testGetRepliesByPostId() {
        Long postId = 1L;
        List<Reply> replies = new ArrayList<>();
        replies.add(new Reply(1L, postId, 1L, "Test Reply 1", LocalDateTime.now()));
        replies.add(new Reply(2L, postId, 1L, "Test Reply 2", LocalDateTime.now()));

        when(replyRepository.findByPostId(postId)).thenReturn(replies);

        List<Reply> result = replyService.getRepliesByPostId(postId);

        assertEquals(2, result.size());
        assertEquals("Test Reply 1", result.get(0).getContent());
        verify(replyRepository, times(1)).findByPostId(postId);
    }

    // 추가적인 수정, 삭제 테스트 케이스를 작성할 수 있습니다.
}

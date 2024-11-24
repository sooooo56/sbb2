package com.mysite.sbb.comment;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 생성
    public Comment createComment(String content, Answer answer, SiteUser author, Comment parent) {
        Comment comment = Comment.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .answer(answer)
                .author(author)
                .parent(parent)
                .build();

        return commentRepository.save(comment);
    }
}

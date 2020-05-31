package com.music.suffer.library.service;

import com.music.suffer.library.domain.entity.Comment;
import com.music.suffer.library.domain.model.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Page<Comment> getComments(Long albumId, Pageable pageable);

    Comment addComment(Long albumId, CommentDTO commentDTO);

    Comment editComment(Long id, CommentDTO commentDTO);

    void deleteComment(Long id);
}

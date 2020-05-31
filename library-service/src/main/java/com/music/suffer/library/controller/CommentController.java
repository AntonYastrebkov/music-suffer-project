package com.music.suffer.library.controller;

import com.music.suffer.library.domain.entity.Comment;
import com.music.suffer.library.domain.model.CommentDTO;
import com.music.suffer.library.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/album/{albumId}/comments")
    public Page<Comment> getComments(
            @PathVariable Long albumId,
            @PageableDefault(sort = {"time"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return commentService.getComments(albumId, pageable);
    }

    @PostMapping("/album/{albumId}/comments")
    public Comment addComment(
            @PathVariable Long albumId,
            @RequestBody CommentDTO commentDTO
    ) {
        return commentService.addComment(albumId, commentDTO);
    }

    @PutMapping("/album/{albumId}/comments/{id}")
    public Comment editComment(
            @PathVariable Long id,
            @RequestBody CommentDTO commentDTO
    ) {
        return commentService.editComment(id, commentDTO);
    }

    @DeleteMapping("/album/{albumId}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}

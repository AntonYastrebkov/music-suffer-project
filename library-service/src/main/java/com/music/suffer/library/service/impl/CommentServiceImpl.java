package com.music.suffer.library.service.impl;

import com.music.suffer.library.domain.entity.Album;
import com.music.suffer.library.domain.entity.Comment;
import com.music.suffer.library.domain.model.CommentDTO;
import com.music.suffer.library.repository.AlbumRepository;
import com.music.suffer.library.repository.CommentRepository;
import com.music.suffer.library.security.model.JwtAuthentication;
import com.music.suffer.library.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AlbumRepository albumRepository;

    @Override
    public Page<Comment> getComments(Long albumId, Pageable pageable) {
        return commentRepository.findByAlbumId(albumId, pageable);
    }

    @Override
    public Comment addComment(Long albumId, CommentDTO commentDTO) {
        Album album = albumRepository.findById(albumId).orElseThrow(() ->
                new EntityNotFoundException("Album with id " + albumId + " not found"));
        Long userId = ((JwtAuthentication) SecurityContextHolder.getContext()
                .getAuthentication())
                .getTokenData()
                .getId();
        Comment commentToSave = new Comment()
                .setText(commentDTO.getText())
                .setScore(commentDTO.getScore())
                .setAuthorId(userId)
                .setAlbum(album)
                .setTime(LocalDateTime.now());
        evaluateAverageScore(album, commentDTO.getScore());
        return commentRepository.save(commentToSave);
    }

    @Override
    public Comment editComment(Long id, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Comment with id " + id + " not found"));
        comment.setText(commentDTO.getText());
        reevaluateAverageScore(comment.getAlbum(), comment.getScore(), commentDTO.getScore());
        comment.setScore(commentDTO.getScore());

        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Comment with id " + id + " not found"));
        Album album = comment.getAlbum();
        int votes = album.getVotes();
        album.setAverageScore(
                (album.getAverageScore() * album.getVotes() - comment.getScore()) / --votes
        );
        album.setVotes(votes);
        commentRepository.delete(comment);
    }

    private void evaluateAverageScore(Album album, int score) {
        int votes = album.getVotes();
        album.setAverageScore((album.getAverageScore() + score) / ++votes);
        album.setVotes(votes);
    }

    private void reevaluateAverageScore(Album album, int oldScore, int newScore) {
        double counted = (album.getAverageScore() * album.getVotes()
                - oldScore + newScore) / album.getVotes();
        album.setAverageScore(counted);
    }
}

package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.exceptions.DataBaseException;
import org.springbootapp.proplus_backendapplication.model.Comment;
import org.springbootapp.proplus_backendapplication.repositories.CommentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        try {
            return commentRepository.save(comment);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }
}

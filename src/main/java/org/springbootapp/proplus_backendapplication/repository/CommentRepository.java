package org.springbootapp.proplus_backendapplication.repository;

import org.springbootapp.proplus_backendapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

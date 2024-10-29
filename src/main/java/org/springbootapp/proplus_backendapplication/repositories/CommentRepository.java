package org.springbootapp.proplus_backendapplication.repositories;

import org.springbootapp.proplus_backendapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

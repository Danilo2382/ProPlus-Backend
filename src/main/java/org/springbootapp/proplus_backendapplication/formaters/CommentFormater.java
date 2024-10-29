package org.springbootapp.proplus_backendapplication.formaters;

import org.springbootapp.proplus_backendapplication.dtos.CommentDto;
import org.springbootapp.proplus_backendapplication.model.Comment;

public class CommentFormater {

    public static CommentDto formatDto(Comment comment) {
        return new CommentDto(
                comment.getText(),
                comment.getTimeStamp()
        );
    }
}

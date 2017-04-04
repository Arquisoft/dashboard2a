package uo.asw.dashboard;

import uo.asw.dbmanagement.model.Comment;

import java.util.List;

/**
 * Created by Irazusta on 03/04/2017.
 */

public interface GetComments {

    Comment getCommentById(Long id);
    List<Comment> getCommentsBySuggestion(Long sugestionId);
    List<Comment> getComments();
}

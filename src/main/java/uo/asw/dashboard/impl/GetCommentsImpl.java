package uo.asw.dashboard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dashboard.GetComments;
import uo.asw.dashboard.GetSuggestions;
import uo.asw.dbmanagement.model.Comment;
import uo.asw.dbmanagement.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetCommentsImpl implements GetComments {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    GetSuggestions suggestionsService;

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public List<Comment> getCommentsBySuggestion(Long sugestionId) {
        return new ArrayList<Comment>(suggestionsService.getSuggestionById(sugestionId).getComments());
    }

    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }


}

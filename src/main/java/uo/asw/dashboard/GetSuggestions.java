package uo.asw.dashboard;

import uo.asw.dbmanagement.model.Suggestion;

import java.util.List;

public interface GetSuggestions {
    Suggestion getSuggestionById(Long id);
    List<Suggestion> getSuggestionsByCitizen(Long citizenId);
    List<Suggestion> getSuggestions();
}

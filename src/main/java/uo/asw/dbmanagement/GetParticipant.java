package uo.asw.dbmanagement;

import uo.asw.dbmanagement.model.Citizen;

public interface GetParticipant {
	Citizen getParticipant(String login, String password);
}

package uo.asw.participants.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbmanagement.GetParticipant;
import uo.asw.dbmanagement.model.Citizen;
import uo.asw.participants.GetParticipantsInfo;
import uo.asw.participants.util.CitizenMin;

@Service
public class GetParticipantsInfoImpl implements GetParticipantsInfo {

	@Autowired
	private GetParticipant citizenDAO;

	@Override
	public CitizenMin getParticipantsInfo(String login, String password) {
		Citizen c = citizenDAO.getParticipant(login, password);
		if (c != null) {
			return new CitizenMin(c.getName(), c.getSurname(), c.getBornDate(), c.getId(), c.getEmail());
		}
		return null;
	}

}

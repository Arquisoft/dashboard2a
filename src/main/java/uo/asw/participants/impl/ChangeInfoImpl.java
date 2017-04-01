package uo.asw.participants.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbmanagement.UpdateInfo;
import uo.asw.dbmanagement.model.Citizen;
import uo.asw.participants.ChangeInfo;

@Service
public class ChangeInfoImpl implements ChangeInfo {

	@Autowired
	private UpdateInfo citizenDAO;

	@Override
	public Citizen changeInfo(Citizen updatedData) {
		return citizenDAO.updateInfo(updatedData);
	}
}

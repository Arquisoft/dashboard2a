package uo.asw.dbmanagement.impl;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import uo.asw.dbmanagement.UpdateInfo;
import uo.asw.dbmanagement.model.Citizen;

@Repository
@Transactional
public class UpdateInfoImpl implements UpdateInfo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Citizen updateInfo(Citizen toUpdate) {
		entityManager.merge(toUpdate);
		return toUpdate;
	}

}

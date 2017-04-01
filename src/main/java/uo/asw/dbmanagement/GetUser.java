package uo.asw.dbmanagement;

import uo.asw.dbmanagement.model.User;

public interface GetUser {
	User getUser(String login, String password);
}

package org.greenpeace.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.greenpeace.model.User;
import org.greenpeace.service.Email02;
import org.greenpeace.controller.RandomPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "forgottenPWBean")
public class ForGottenPwBean {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ForGottenPwBean.class);

	
	/** The user. */
	private User user = new User();

	public void forgottenPW(ActionEvent actionEvent) {
		LOGGER.debug("username: {}", user.getAccount());
		LOGGER.debug("email: {}", user.getEmail());

		String pw = new RandomPassword().generate();
		LOGGER.debug("random : {}", pw);

		// send email
		new Email02(user.getEmail(), user.getAccount(), pw);

		// reset user data
		this.user = new User();
		
		LOGGER.debug("已寄信");
	}

	public User getMember() {
		return user;
	}

	public void setMember(User member) {
		this.user = member;
	}

}
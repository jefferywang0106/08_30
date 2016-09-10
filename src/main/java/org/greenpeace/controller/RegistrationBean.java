package org.greenpeace.controller;

import java.io.PrintStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.greenpeace.dao.UserDAO;
import org.greenpeace.dao.UserDAOImpl;
import org.greenpeace.model.Member;
import org.greenpeace.model.User;
import org.greenpeace.model.User;
import org.greenpeace.service.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "RegistrationCtrl")
public class RegistrationBean {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationBean.class);

	public Member member = new Member();

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void Registration() {

		LOGGER.debug("username: {}", member.getAccount());
		LOGGER.debug("password: {}", member.getPassword());
		LOGGER.debug("email: {}", member.getEmail());

		UserDAO dao = new UserDAOImpl();
		if (dao.getAllUser(member.getAccount()) != null) {
			LOGGER.debug("帳號已重複", member.getAccount());
			return;

		}

		member.setmType("一般會員");
		dao.createOrUpdateUser(member);
		// new Email("", member.getEmail(), member.getAccount(),
		// member.getPassword());

		if (member != null) {
			LOGGER.debug("註冊成功!!");
		} else {
			LOGGER.debug("註冊失敗!!");
		}
	}

}
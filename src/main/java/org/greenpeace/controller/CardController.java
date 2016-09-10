package org.greenpeace.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import org.greenpeace.dao.Database;
import org.greenpeace.dao.UserDAOImpl;
import org.greenpeace.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "CardCtrl")
public class CardController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

	private Member member = new Member();

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String Card() {

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";

		UserDAOImpl uDao = new UserDAOImpl();
		

		LOGGER.debug("username: {}", member.getCash());
		LOGGER.debug("username: {}", member.getAccount());
		int oldCash = 0;
		int addCash = member.getCash().intValue();
		
		Timestamp now = new Timestamp(System.currentTimeMillis());

		if (uDao.checkUser(member.getAccount())) {
			try {
				Database db = new Database();
				conn = db.getConnection();

				ps = conn.prepareStatement("select cash from member where account = ?");
				ps.setString(1, member.getAccount());
				rs = ps.executeQuery();
				rs.next();
				oldCash = rs.getInt(1);

				ps = conn.prepareStatement("insert into deposit values(null,?,?,?)");
				ps.setBigDecimal(1, member.getCash());
				ps.setTimestamp(2, now);
				ps.setString(3, member.getAccount());
				ps.execute();

				ps = conn.prepareStatement("update member set cash = ? where account = ?");	
				ps.setInt(1, oldCash + addCash);
				ps.setString(2, member.getAccount());
				ps.execute();

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			} finally {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (Exception e2) {
					LOGGER.error(e2.getMessage(), e2);
				}
			}
			LOGGER.debug("儲值成功: {}", member.getCash());
			return null;
		}else{
			LOGGER.debug("儲值失敗: {}", member.getCash());
			return null;
		}
	}
}
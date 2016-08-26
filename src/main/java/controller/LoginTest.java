package controller;

import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import dao.*;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import service.Email02;
//import model.CustomerSuper;
//import model.Deposit;
//import model.Member;
//import model.Order;
//import model.OrderSuper;

@ManagedBean(name = "login")
public class LoginTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
	private String username;
	private String password;

	public String login() {
		LOGGER.debug("login!!");
		LOGGER.debug("username: {}", username);
		LOGGER.debug("password: {}", password);
		return "service.xhtml";
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
    public void getPage() {
        System.out.println("good morninig!");
        
        
        
        
        
        
     }
	
	
	
	
	

//	public class Login extends HttpServlet {
//		private static final long serialVersionUID = 1L;
//
//		private UserDAO dao = new UserDAOImpl();
//
//		/**
//		 * @see HttpServlet#HttpServlet()
//		 */
//		public Login() {
//			super();
//			// TODO Auto-generated constructor stub
//		}
//
//		/**
//		 * @see HttpServlet#doGet(HttpServletRequest request,
//		 *      HttpServletResponse response)
//		 */
//		protected void doGet(HttpServletRequest request, HttpServletResponse response)
//				throws ServletException, IOException {
//
//			doPost(request, response);
//		}
//
//		/**
//		 * @see HttpServlet#doPost(HttpServletRequest request,
//		 *      HttpServletResponse response)
//		 */
//
//		protected void doPost(HttpServletRequest request, HttpServletResponse response)
//				throws ServletException, IOException {
//
//			String account = request.getParameter("account");
//			String password = request.getParameter("password");
//			String email = request.getParameter("email");
//			String againaccount = request.getParameter("againaccount");
//			String againemail = request.getParameter("againemail");
//
//			String actionType = request.getParameter("type");
//			System.out.println("type : " + actionType);
//
//			int z;
//			StringBuilder sb = new StringBuilder();
//			int i;
//			for (i = 0; i < 5; i++) {
//				z = (int) ((Math.random() * 7) % 3);
//
//				if (z == 1) { // 放數字
//					sb.append((int) ((Math.random() * 10) + 48));
//				} else if (z == 2) { // 放大寫英文
//					sb.append((char) (((Math.random() * 26) + 65)));
//				} else {// 放小寫英文
//					sb.append(((char) ((Math.random() * 26) + 97)));
//				}
//			}
//
//			System.out.println("sb = " + sb);
//
//			System.out.println("againaccount" + againaccount);
//			System.out.println("sb.toString()" + sb.toString());
//			System.out.println("againemail" + againemail);
//
//			// 修改密碼
//			if ("changePassword".equals(actionType)) {
//
//				System.out.println(againaccount);
//				System.out.println(againemail);
//
//				response.setContentType("text/html;charset=utf-8");
//
//				PrintWriter out = response.getWriter();
//
//				int User = dao.updatePassword(againaccount, sb.toString(), againemail);
//
//				if (User != 0) {
//					out.println("<script type=\"text/javascript\">");
//					out.println("alert('密碼修改完成，請至您的信箱收一下更改的密碼。');");
//					out.println("location='index.xhtml';");
//					out.println("</script>");
//
//					new Email02(againemail, againaccount, sb.toString());
//
//					System.out.println(againaccount);
//					System.out.println(againemail);
//					System.out.println(sb.toString());
//
//					return;
//				} else {
//					out.println("<script type=\"text/javascript\">");
//					out.println("alert('很抱歉，帳號及信箱輸入錯誤。密碼無法修改唷。');");
//					out.println("location='index.xhtml';");
//					out.println("</script>");
//					return;
//				}
//			}
//
//			// 登入
//			Member member = dao.getUserByAccount(account, password);
//
//			System.out.println(account);
//
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter out = response.getWriter();
//
//			if (member == null) {
//				out.println("<script type=\"text/javascript\">");
//				out.println("alert('帳號密碼輸入錯誤');");
//				out.println("location='index.xhtml';");
//				out.println("</script>");
//				return;
//			}
//
//		}
//	}
}

package org.greenpeace.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.greenpeace.dao.CustomerDAO;
import org.greenpeace.dao.MemberOrderDAO;
import org.greenpeace.dao.OrderDAO;
import org.greenpeace.dao.StartDAO;
import org.greenpeace.model.Customer;
import org.greenpeace.model.CustomerDto;
import org.greenpeace.model.Item;
import org.greenpeace.model.Member;
import org.greenpeace.model.Order;
import org.greenpeace.model.Product;
import org.greenpeace.model.ProductDto;
import org.greenpeace.model.Start;
import org.greenpeace.service.CustomerService;
import org.greenpeace.service.CustomerServicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "cpmoney")
@RequestScoped
public class CompareMoneyController implements Serializable {
	private static final long serialVersionUID = 1715137869469866421L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CompareMoneyController.class);
	private StartDAO startDAO = new StartDAO();
	CustomerDAO dao = new CustomerDAO();
	public String compareMoney(List<ProductDto> pro) {
		OrderDAO oDao = new OrderDAO();
		BigDecimal total = new BigDecimal(0);
		int f = 1;
		int total1 = 0;
		for (ProductDto prodto : pro) {

			int price = prodto.getData().getPrice();
			int number = prodto.getNumber();
			total1 += price * number;

			LOGGER.debug(Integer.toString(prodto.getNumber()));
			LOGGER.debug(prodto.getData().getName());
			LOGGER.debug(Integer.toString(prodto.getData().getPrice()));

		}

		total = new BigDecimal(total1);

		LOGGER.debug(total.toString());

		FacesContext context = FacesContext.getCurrentInstance();// session用
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String account = (String) session.getAttribute("account");
		
		Member member = new Member();
		member.setAccount(account);
		
		BigDecimal cash = (BigDecimal) session.getAttribute("cash");

		Timestamp c_date = new Timestamp(new Date().getTime());
		int res;
		res = cash.compareTo(total);

		LOGGER.debug(Integer.toString(res));

		if (res > 0) {
			LOGGER.debug("結帳成功");

			MemberOrderDAO moDao = new MemberOrderDAO();
			String id = (String) session.getAttribute("id");

			moDao.insert(account, Integer.parseInt(id), c_date);

			for (ProductDto prodto : pro) {

				if (prodto.getNumber() != 0) {
					String productName = prodto.getData().getName();
					int price = prodto.getData().getPrice();
					int number = prodto.getNumber();
					int order_id = Integer.parseInt(id); // ??
					int item_id = 0 ;
					List<Start> list = startDAO.getOrderDatalist(Integer.parseInt(id));
					for (Start data : list) {
						item_id= data.getItem_id();
					
					}
					int money = price * number;
					Item item = new Item();
					item.setId(item_id);
					
					
					Customer customer = new Customer();
					customer.setItem(item);
					customer.setMember(member);
					customer.setCounts(number);
					customer.setMoney(money);
					customer.setStatus("未取餐");
					customer.setCDate(c_date);
					customer.setOrder_id(order_id);
	
					dao.createCustomer(customer);
					
					
				}

			}

			return null;

		}
		return null;
	}
}

// private CustomerDto data;
// private CustomerService service = new CustomerServicesImpl();
// @PostConstruct
// protected void postMethod(){
// data =new CustomerDto();
// data.setPo(new Customer());
// }
// public String compareMoney(List<ProductDto> pro) {
// // 會員餘額是否大於結帳金額
// CustomerDto tmp = service.logic( pro);
// data.setPo(new Customer());
//
// if (tmp.isOk()) {
// return compareStatus(data);
// } else {
// return "failure";
// }
// }
//
// public String compareStatus(CustomerDto data) {
// String result = "orderStillExist";
//
// // 結帳金額是否大於結標金額
// boolean ok = service.judgeExist(data);
//
// if(!ok){
// result="訂單消失";
// }
// return result;
// }
//
//
//
//
//
//
//
//
// public CustomerDto getData() {
// return data;
// }
//
// public void setData(CustomerDto data) {
// this.data = data;
// }
//
// public void setService(CustomerService service) {
// this.service = service;
// }
//

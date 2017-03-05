package com.ogg.erp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.ogg.erp.model.Account;
import com.ogg.erp.repository.AccountRepository;

public class CheckAccount extends HttpServlet
{

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -5006436595046106300L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Account account = new AccountRepository().getAccount(username);

		if (account != null)
		{
			if (StringUtils.isNotEmpty(username) && (username.trim().equals(account.getUsername())))
			{
				if (StringUtils.isNotEmpty(password) && (password.trim().equals(account.getPassword())))
				{
					System.out.println("User: " + username + " login success");
					session.setAttribute("username", username);
					String login_sucess = "index.jsp";
					resp.sendRedirect(login_sucess);
					return;
				}
			}
		}

		String login_fail = "login.jsp";
		resp.sendRedirect(login_fail);
		return;
	}
}
package cn.pjj.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pjj.bean.User;
import cn.pjj.user.service.BusinessService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			BusinessService bus = new BusinessService();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(username==null||username.equals("")){
				request.setAttribute("user", "请输入用户名");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return;
			}
			request.setAttribute("username", username);//用于回显用户所填的用户名
			User user = bus.login(username, password);
			if(user!=null){
				//登录成功跳转到index页面
				request.setAttribute("message","欢迎你:"+user.getNickname());
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
			}else{
				//如果数据库中不存在提示用户不存在
				request.setAttribute("message", "密码错误!");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			}
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		}

}

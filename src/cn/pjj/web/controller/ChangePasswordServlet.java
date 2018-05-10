package cn.pjj.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.pjj.exception.AnswerErrorException;
import cn.pjj.exception.UserNotExistException;
import cn.pjj.user.service.BusinessService;
import cn.pjj.utils.WebUtils;
import cn.pjj.web.bean.ChangePasswordBean;

public class ChangePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			ChangePasswordBean bean = WebUtils.request2Bean(request, ChangePasswordBean.class);
			HttpSession session = request.getSession(false);
			int index = (Integer) session.getAttribute("index");//从session中得到传过来的index
			BusinessService bus = new BusinessService();
			if(!bean.checkForm()){//判断填写的信息是否符合要求
				session.setAttribute("bean", bean);
				response.sendRedirect(request.getContextPath()+"/servlet/ChangePasswordUIServlet");
				return;
			}
			try {
				bus.changePassword(bean.getUsername(),bean.getPassword1(),index, bean.getAnswer());
				request.setAttribute("message","恭喜你密码修改成功!");
				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
				return;
			} catch (AnswerErrorException e) {
				session.setAttribute("mess","问题回答错误!");
				//此处利用转发是因为重定向会使得随机生成的问题不显示
				response.sendRedirect(request.getContextPath()+"/servlet/ChangePasswordUIServlet");
				return;
			} catch (UserNotExistException e) {
				session.setAttribute("message","用户不存在!!");
				//此处利用转发是因为重定向会使得随机生成的问题不显示
				response.sendRedirect(request.getContextPath()+"/servlet/ChangePasswordUIServlet");
				return;
			}
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
	}

}

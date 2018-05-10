package cn.pjj.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pjj.bean.User;
import cn.pjj.exception.UserExistException;
import cn.pjj.user.service.BusinessService;
import cn.pjj.utils.WebUtils;
import cn.pjj.web.bean.RegisterFormBean;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			//将表单信息封装到bean中
			RegisterFormBean registerFormBean = WebUtils.request2Bean(request,RegisterFormBean.class);
			boolean flag = registerFormBean.checkForm();//检查用户填写的数据是否符合格式要求
			//校验失败提示用户,并将原有的数据存入域中
			if(!flag){
				request.setAttribute("formBean",registerFormBean);//用于回显用户所填的数据
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
				return;
			}
			//校验成功处理注册请求
			BusinessService bus =  new BusinessService();
			User user = new User();
			WebUtils.copyBean(registerFormBean, user);//将formbean中数据拷贝到user中
			try {
				//注册成功,处理注册请求并通知用户
				bus.register(user);
				request.setAttribute("message","恭喜你注册成功!");
				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
				return;
			} catch (UserExistException e) {
				//如果service处理不成功,原因是用户名已存在,跳转到注册页面提醒
				request.setAttribute("message","注册的用户已经存在!!!");
				request.setAttribute("formBean",registerFormBean);//用于回显用户所填的数据
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
				return;
			} catch(Exception e){
				//如果是其他原因,跳转到网站首页,显示友好错误
				request.setAttribute("message", "服务器出现错误,请稍候在试");
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
				return;
			}
		}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		}
}

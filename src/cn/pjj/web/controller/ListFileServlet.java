package cn.pjj.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pjj.bean.Upfile;
import cn.pjj.bean.User;
import cn.pjj.bean.QueryInfo;
import cn.pjj.bean.PageBean;
import cn.pjj.factory.Factory;
import cn.pjj.service.BusinessService;
import cn.pjj.utils.WebUtils;

public class ListFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("user")==null){
			request.setAttribute("message","用户未登录");
			request.getRequestDispatcher("/message.jsp").forward(
					request, response);
		}
		BusinessService bus = Factory.getInstance().createImpl(BusinessService.class);
/*		List<Upfile> list = bus.getAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request,
				response);*/
		request.setCharacterEncoding("utf-8");
		QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
		String totalPage = request.getParameter("totalPage");
		if(totalPage!=null&&totalPage!=""){//防止跳转页面超过最大值bug
			if(info.getCurrentPage()>Integer.parseInt(totalPage)){
				info.setCurrentPage(Integer.parseInt(totalPage));
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		PageBean bean = bus.pageQuery(info,user.getUsername());
		request.setAttribute("pagebean", bean);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(
				request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

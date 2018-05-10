package cn.pjj.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.pjj.bean.Upfile;
import cn.pjj.exception.UploadNULLException;
import cn.pjj.factory.Factory;
import cn.pjj.service.BusinessService;
import cn.pjj.utils.WebUtils;

public class UpdateFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			request.setAttribute("message", "用户未登录");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		String id = request.getParameter("id");
		BusinessService bus = Factory.getInstance().createImpl(BusinessService.class);
		Upfile file = bus.find(id);
		request.setAttribute("file", file);
		request.getRequestDispatcher("/WEB-INF/jsp/change.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")==null){
			request.setAttribute("message","用户未登录");
			request.getRequestDispatcher("/message.jsp").forward(
					request, response);
		}
		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("message", "不支持的操作");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		try {
			BusinessService bus = Factory.getInstance().createImpl(BusinessService.class);
			String path = "/";
			Upfile upfile = WebUtils.upload(request, path);
			bus.update(upfile);
			request.setAttribute("message", "修改成功");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (FileSizeLimitExceededException e) {
			request.setAttribute("message", "请上传小于500m的文件");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (UploadNULLException e) {
			request.setAttribute("message", "上传文件不能为空");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "修改失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

}

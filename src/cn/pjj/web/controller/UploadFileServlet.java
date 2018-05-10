package cn.pjj.web.controller;

import java.io.IOException;
import java.util.UUID;

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
import cn.pjj.utils.IDUtils;
import cn.pjj.utils.WebUtils;

public class UploadFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			request.setAttribute("message", "用户未登录");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			request.setAttribute("message", "用户未登录");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
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
			/*
			 * 此处生成id而不在webUtils中生成是因为update时候会
			 * 用到webutils而更新时候又需要id所以id不同数据库会更新失败 所以在jsp中设置<input type="hidden"
			 * value="${file.id}" name="id"> 在webutils中利用beanutils将id整到upfile对象中
			 */
			upfile.setId(IDUtils.genItemId() + "");
			bus.add(upfile);
			request.setAttribute("message", "上传成功");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (FileSizeLimitExceededException e) {
			request.setAttribute("message", "请上传小于500m的文件");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (UploadNULLException e) {
			request.setAttribute("message", "上传文件不能为空");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "上传失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
}

package cn.pjj.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.fs.Hdfs;

import cn.pjj.bean.Upfile;
import cn.pjj.factory.Factory;
import cn.pjj.service.BusinessService;
import cn.pjj.utils.HdfsUtils;

public class DeleteFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			BusinessService bus = Factory.getInstance().createImpl(BusinessService.class);
			Upfile find = bus.find(id);
			HdfsUtils.deletedir("/" + find.getUuidname());
			bus.delete(id);
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (IOException e) {
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

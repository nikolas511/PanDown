package cn.pjj.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.fs.Hdfs;

import cn.pjj.bean.Upfile;
import cn.pjj.factory.Factory;
import cn.pjj.service.BusinessService;
import cn.pjj.utils.HdfsUtils;

public class DownLoadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String id = request.getParameter("id");
			BusinessService bus = Factory.getInstance().createImpl(BusinessService.class);
			Upfile upfile = bus.find(id);
			if(upfile==null){
				request.setAttribute("message", "对不起,你要下载的文件已被删除!");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			HdfsUtils.downFile(upfile, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		}

}

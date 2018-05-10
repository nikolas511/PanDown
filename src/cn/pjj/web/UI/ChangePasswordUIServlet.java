package cn.pjj.web.UI;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] str = {" 生日 "," 邮箱 "," 昵称 "};//随机生成问题
		Random random = new Random();
		int index = random.nextInt(3);
		request.setAttribute("question", str[index]);//页面随机输出问题确认用户是否是本人
		HttpSession session = request.getSession();
		session.setAttribute("index", index);//将index存入session中传到controller修改密码处使用
		request.getRequestDispatcher("/WEB-INF/jsp/changePassword.jsp").forward(request, response);
		session.removeAttribute("bean");//从session中移除已经显示过的信息避免重复显示
		session.removeAttribute("mess");
		session.removeAttribute("message");
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		}

}

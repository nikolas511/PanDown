package cn.pjj.user.service;

import cn.pjj.bean.User;
import cn.pjj.dao.UserDao;
import cn.pjj.exception.AnswerErrorException;
import cn.pjj.exception.UserExistException;
import cn.pjj.exception.UserNotExistException;
//对web层提供注册 登录 修改密码服务
public class BusinessService {
	private UserDao dao = new UserDao();
	public void register(User user) throws UserExistException{
		if(!dao.find(user.getUsername()))//检查用户是否存在 (true为存在false为不存在)
			dao.addUser(user);
		else{
			throw new UserExistException();//用户存在抛出新异常
		}
	}
	public User login(String username,String password){
		 return dao.findUser(username, password);//执行登录语句 (若密码错误等返回null)
	}
	public void changePassword(String username,String newPassword,
			int index,String answer) throws AnswerErrorException, UserNotExistException{
		if(dao.find(username)){//先查看用户是否存在
			if(dao.checkUser(username, index, answer)){//用户信息匹配为true
				dao.changePassword(username, newPassword);
			}else{
				throw new AnswerErrorException();//问题回答错误异常
			}
		}else{
			throw new UserNotExistException();//用户不存在异常
		}
	}
}

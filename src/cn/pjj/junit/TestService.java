package cn.pjj.junit;

import org.junit.Test;

import cn.pjj.bean.User;
import cn.pjj.exception.AnswerErrorException;
import cn.pjj.exception.UserExistException;
import cn.pjj.exception.UserNotExistException;
import cn.pjj.user.service.BusinessService;

public class TestService {
	@Test
	public void testRegister(){
		BusinessService bs = new BusinessService();
		User user = new User();
		user.setUsername("eee");
		user.setPassword("123456");
		user.setBirthday("1987-10-06");
		user.setEmail("111@qq.com");
		user.setNickname("老李");
		try {
			bs.register(user);
		} catch (UserExistException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testLogin(){
		BusinessService bs = new BusinessService();
		User user = bs.login("sss","123456");
	}
	@Test
	public void testChangePassword(){
		BusinessService bs = new BusinessService();
		try {
			bs.changePassword("ccc", "123456", 1, "1995-01-05");
		} catch (AnswerErrorException e) {
			e.printStackTrace();
		} catch (UserNotExistException e) {
			e.printStackTrace();
		}
	}
}

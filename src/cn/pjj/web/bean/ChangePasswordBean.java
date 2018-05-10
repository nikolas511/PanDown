package cn.pjj.web.bean;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordBean {
	private String username;
	private String answer;
	private String password1;
	private String password2;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	//1.校对用户名是否符合6-8位 (数字或字母组成)
	//2.校对密码是否符合6-8位 (数字或字母组成)两次password需要相同
	private Map mistakes = new HashMap();
	public Map getMistakes() {
		return mistakes;
	}
	public void setMistakes(Map mistakes) {
		this.mistakes = mistakes;
	}
	public boolean checkForm(){
		boolean flag = true;
		if(username==null){
			flag=false;
			this.mistakes.put("username","用户名不能为空");
		}else{
			if(!this.username.matches("[A-Za-z0-9]{3,8}")){
				flag=false;
				this.mistakes.put("username", "用户名必须3-8位字母和数字!!!");
			}
		}
		if(this.password1==null||this.password1.equals("")){
			flag = false;
			this.mistakes.put("password1","密码不能为空!!!");
		}else{
			if(!this.password1.matches("[A-Za-z0-9]{3,8}")){
				flag=false;
				this.mistakes.put("password1", "密码必须3-8位字母和数字!!!");
			}
		}
		if(this.password2==null||this.password2.equals("")){
			flag = false;
			this.mistakes.put("password2","密码不能为空!!!");
		}else{
			if(!this.password2.equals(this.password1)){
				flag=false;
				this.mistakes.put("password2","两次密码不同!!!");
			}
		}
		return flag;
	}
}

package cn.pjj.web.bean;

import java.util.HashMap;
import java.util.Map;

public class RegisterFormBean {
	private String username;
	private String password;
	private String password2;
	private String birthday;
	private String email;
	private String nickname;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	//1.校对用户名是否符合6-8位 (数字或字母组成)
	//2.校对密码是否符合6-8位 (数字或字母组成)两次password需要相同
	//3.检查邮箱是否符合xxx@xxx.com格式
	//4.检查生日是否填写,若填写则需要符合yyyy-mm-dd格式
	//5.检查nickname是否为中文
	private Map errors = new HashMap();//错误信息存在Map中
	public Map getErrors() {
		return errors;
	}
	public void setErrors(Map errors) {
		this.errors = errors;
	}
	public boolean checkForm(){
		boolean flag=true;
		if(this.username==null||this.username.equals("")){
			flag = false;
			this.errors.put("username","用户名不能为空!!!");
		}else{
			if(!this.username.matches("[A-Za-z0-9]{3,8}")){
				flag=false;
				this.errors.put("username", "用户名必须3-8位字母和数字!!!");
			}
		}
		if(this.password==null||this.password.equals("")){
			flag = false;
			this.errors.put("password","密码不能为空!!!");
		}else{
			if(!this.password.matches("[A-Za-z0-9]{3,8}")){
				flag=false;
				this.errors.put("password", "密码必须3-8位字母和数字!!!");
			}
		}
		if(this.password2==null||this.password2.equals("")){
			flag = false;
			this.errors.put("password2","密码不能为空!!!");
		}else{
			if(!this.password2.equals(this.password)){
				flag=false;
				this.errors.put("password2","两次密码不同!!!");
			}
		}
		if(this.email==null||this.email.equals("")){
			flag = false;
			this.errors.put("email","邮箱不能为空!!!");
		}else{
			if(!this.email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
				flag=false;
				this.errors.put("email","请输入正确邮箱格式!!!");
			}
		}
		if(this.birthday!=null&&!this.equals("")){
			if(!this.birthday.matches("^\\d{4}(-)\\d{1,2}\\1\\d{1,2}$")){
				this.errors.put("birthday", "请输入正确的生日格式yyyy-mm-dd");
			}
		}
		if(this.nickname==null||this.nickname.equals("")){
			flag = false;
			this.errors.put("nickname","昵称不能为空!!!");
		}else{
			if(!this.nickname.matches("([\u4e00-\u9fa5]+)")){
				flag=false;
				this.errors.put("nickname","请输入中文昵称!!!");
			}
		}
		return flag;
	}
}

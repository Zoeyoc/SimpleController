package action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;

public class LoginAction implements ActionInterface{
	private String username;
	private String password;
	public Object login() {
		
		Object uri = null;

		UserBean userBean = new UserBean(username,password);
		if (userBean.checkUser()) {
			uri = "success";
		} else {
			uri = "fail";
		}
		return uri;
	}
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
	@Override
	public Object doSomething(String actionClazzMethod) {
		Object returnFlag=null;
		try {
			Method method = this.getClass().getDeclaredMethod(actionClazzMethod);
			returnFlag = method.invoke(this);
		} catch (Exception e){
			e.printStackTrace();
		}
		return returnFlag;
	}
}

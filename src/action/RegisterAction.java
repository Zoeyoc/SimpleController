package action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;

public class RegisterAction implements ActionInterface{
	private String username;
	private String password;
	public Object register() {
		
		UserBean userBean = new UserBean(username,password);
		if (userBean.checkUser()) {
			return "success";
		} else {
			return "fail";
		}
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

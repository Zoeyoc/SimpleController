package controller;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import interceptor.Interceptor;


@XmlRootElement(name = "action-controller")
public class ActionController {
	private List<Action> actionList;
    private List<Interceptor> interceptorList;
    
	@XmlElement(name = "action")
	public List<Action> getActionlist() {
		return actionList;
	}

	public void setActionlist(List<Action> actionlist) {
		this.actionList = actionlist;
	}
	
	@XmlElement(name = "interceptor")
	public List<Interceptor> getInterceptorList() {
		return interceptorList;
	}

	public void setInterceptorList(List<Interceptor> interceptorList) {
		this.interceptorList = interceptorList;
	}

	@Override
	public String toString() {
		return "ActionController [actionlist=" + actionList + ",interceptorlist=" + interceptorList + "]";
	}

	
	
}

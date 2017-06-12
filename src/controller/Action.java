package controller;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import interceptor.InterceptorRef;


@XmlRootElement(name = "action")
public class Action {
	private String name;
	private ActionClass actionClass;
	private InterceptorRef interceptorRef = null;
	private List<Result> resultList;
	
	
	
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "class")
	public ActionClass getActionclass() {
		return actionClass;
	}
	public void setActionclass(ActionClass actionclass) {
		this.actionClass = actionclass;
	}
	
	@XmlElement(name = "interceptor-ref")
	public InterceptorRef getInterceptorRef() {
		return interceptorRef;
	}
	public void setInterceptorRef(InterceptorRef interceptorRef) {
		this.interceptorRef = interceptorRef;
	}
	
	@XmlElement(name = "result")
	public List<Result> getResultList() {
		return resultList;
	}
	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}
	
	@Override
	public String toString() {
		return "Action [name=" + name + ", actionclass=" + actionClass + ", interceptorRef=" + interceptorRef + ",resultList=" + resultList + "]";
	}
	
	
}

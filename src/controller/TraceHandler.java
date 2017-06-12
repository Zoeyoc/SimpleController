package controller;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import interceptor.ActionInterceptor;


public class TraceHandler implements InvocationHandler {
	private Object target;
	private ActionInterceptor actionInterceptor;
	private String name;
	private String result;
	public TraceHandler(Object object,String interceptorClazzName, String interceptorClazzMethod) {
		this.target=object;
		this.actionInterceptor = new ActionInterceptor(interceptorClazzName, interceptorClazzMethod);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//在 Action 执行之前，使用LogWriter 记录 Action 的名称、类型、访问开始时间
		this.name=(String)args[0];
		this.actionInterceptor.before(this.name);
		Object returnFlag = method.invoke(this.target, args);
		//在 Action 执行之后记录 Action 访问结束时间，及返回的结果 result
		this.result=(String)returnFlag;
		this.actionInterceptor.after(this.result);
		return returnFlag;
	}
}

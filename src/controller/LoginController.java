package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import action.ActionInterface;

import action.LoginAction;
import action.RegisterAction;
import bean.UserBean;
import interceptor.Interceptor;
import interceptor.InterceptorRef;

public class LoginController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    // 1.获取请求uri,得到请求路径名称 如login
			String uri = req.getRequestURI();
		    // 获取请求的action
			String actionName = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".scaction"));
		
			
		try {
			 //读入xml文件
			File file = new File(LoginController.class.getResource("/controller.xml").getPath());
			//加载映射bean类  
			JAXBContext jaxbContext = JAXBContext.newInstance(ActionController.class);
			//创建解析  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ActionController a = (ActionController) jaxbUnmarshaller.unmarshal(file);
			System.out.println(a);
			
			//查找对应 name 的 action
			List<Action> actions = a.getActionlist();
			Action rightAction = null;
			for (int i = 0; i < actions.size(); i++) {
				Action tempAction = actions.get(i);
				if (tempAction.getName().equals(actionName)) {
					rightAction = tempAction;
				}
			}
			System.out.println(rightAction);
			
//			Date date = new Date();
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String stime = dateFormat.format(date);
				
				
			List<Result> results = rightAction.getResultList();    
			String className = rightAction.getActionclass().getName();  //得到class
			String method = rightAction.getActionclass().getMethod();   //得到method
			
			//控制器检查该 action 是否配置了 LogWriter 拦截器
			InterceptorRef interceptorref = rightAction.getInterceptorRef();
			Interceptor rightInterceptor = null ;
		    if(interceptorref != null ){
			//<interceptor-ref>指向<action-controller>中已定义的<interceptor>节点
			List<Interceptor> interceptors = a.getInterceptorList();
			for (int i = 0; i < interceptors.size(); i++) {
				Interceptor tempInterceptor = interceptors.get(i);
				if (tempInterceptor.getName().equals(interceptorref.getName())) {
					rightInterceptor = tempInterceptor;

					}	
				}	
		    }
			System.out.println(rightInterceptor);			
			
			
			
//			
			String result="";
			
			try {
				// 反射机制，创建对象
				Class<?> clazz = Class.forName(className);
				Object o = clazz.newInstance();
				String[] keys = new String[req.getParameterMap().keySet().size()];
				req.getParameterMap().keySet().toArray(keys);
				System.out.println(keys[0]+keys[1]);
				for (int i = 0; i < keys.length; i++) {
					Field field = clazz.getDeclaredField(keys[i]);
					System.out.println(field.getName());
					field.setAccessible(true);
					field.set(o, req.getParameterMap().get(keys[i])[0]);// 并不调用setUsername()
					System.out.println(req.getParameterMap().get(keys[i])[0]);
				}
				Method m = clazz.getDeclaredMethod(method);
				result = (String) m.invoke(o);
				
				// 获取action的拦截映射
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			if(rightInterceptor==null){
				for (int i=0; i < results.size(); i++) {
					if (results.get(i).getName().equals(result)){
						if (results.get(i).getType().equals("forward")) {
							if (results.get(i).getValue().endsWith("xml")) {
								xml2Html(results.get(i).getValue(), req, resp);
							} else {
								req.getRequestDispatcher(results.get(i).getValue()).forward(req, resp);				
							}
						}else if (results.get(i).getType().equals("redirect")) {
							resp.sendRedirect(results.get(i).getValue());
						}
					}
				}
			}
			else{
			
				try {
			         // 反射机制，创建对象
					Class<?> clazz = Class.forName(className);
					Object o = clazz.newInstance();
					String[] keys = new String[req.getParameterMap().keySet().size()];
					req.getParameterMap().keySet().toArray(keys);
					for (int i = 0; i < keys.length; i++) {
						Field field = clazz.getDeclaredField(keys[i]);
						field.setAccessible(true);
						field.set(o, req.getParameterMap().get(keys[i])[0]);
					}
					InvocationHandler handler = new TraceHandler(o, rightInterceptor.getInterceptorClass().getName(),
					rightInterceptor.getInterceptorClass().getMethod());
					ActionInterface actionProxy = (ActionInterface) Proxy.newProxyInstance(o.getClass().getClassLoader(),
					o.getClass().getInterfaces(), handler);
					// 调用方法并保存返回的标记
					result = (String) actionProxy.doSomething(rightAction.getActionclass().getMethod());
			
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
			}
			 catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			for (int i=0; i < results.size(); i++) {
				if (results.get(i).getName().equals(result)){
					if (results.get(i).getType().equals("forward")) {
						if (results.get(i).getValue().endsWith("xml")) {
							xml2Html(results.get(i).getValue(), req, resp);
						} else {
							req.getRequestDispatcher(results.get(i).getValue()).forward(req, resp);				
						}
					}else if (results.get(i).getType().equals("redirect")) {
						resp.sendRedirect(results.get(i).getValue());
					}
				}
			}
			}
		}catch (JAXBException e) {
			e.printStackTrace();
		
		}
	}
	

	private void xml2Html(String xmlPath, HttpServletRequest req, HttpServletResponse resp) {
		try {
			String rootPath = req.getSession().getServletContext().getRealPath("/");
			TransformerFactory factory = TransformerFactory.newInstance();
			Templates template = factory.newTemplates(new StreamSource(new FileInputStream(rootPath + "pages/success_view.xsl")));
	        Transformer xformer = template.newTransformer();
	        Source source = new StreamSource(new FileInputStream(rootPath + xmlPath));
	        StreamResult result = new StreamResult(resp.getOutputStream());
	        xformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	

}

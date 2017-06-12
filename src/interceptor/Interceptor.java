package interceptor;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "interceptor")
public class Interceptor {
	private String name;
	private InterceptorClass interceptorClass;
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "class")
	public InterceptorClass getInterceptorClass() {
		return interceptorClass;
	}
	public void setInterceptorClass (InterceptorClass interceptorClass) {
		this.interceptorClass = interceptorClass;
	}
	
	
	
	@Override
	public String toString() {
		return "Interceptor [name=" + name + ", interceptorClass=" + interceptorClass + "]";
	}
	
	
}

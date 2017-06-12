package interceptor;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "class")
public class InterceptorClass {
	private String name;
	private String method;
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	@Override
	public String toString() {
		return "InterceptorClass [name=" + name + ", method=" + method + "]";
	}
	
	

}

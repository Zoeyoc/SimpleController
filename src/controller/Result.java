package controller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.omg.PortableInterceptor.IORInterceptor;

@XmlRootElement(name = "result")
public class Result {
	private String name;
	private String type;
	private String value;
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Result [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	
}

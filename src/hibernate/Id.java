package hibernate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "id")
public class Id {
	private String name;
	
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Id [name=" + name + "]";
	}
}

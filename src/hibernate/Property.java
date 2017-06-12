package hibernate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "property")
public class Property {
	private String name;
	private String value;
	private String column;
	private String type;
	private String lazy;
	@XmlElement(name = "column")
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	@XmlElement(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement(name = "lazy")
	public String getLazy() {
		return lazy;
	}
	public void setLazy(String lazy) {
		this.lazy = lazy;
	}
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name = "value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Property [name=" + name + ", value=" + value + ", column=" + column + ", type=" + type + ", lazy="
				+ lazy + "]";
	}
	
}

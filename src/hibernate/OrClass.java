package hibernate;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "class")
public class OrClass {
	private String name;
	private String table;
	private Id id;
	private List<Property> propertyList;
	
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name = "table")
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	@XmlElement(name = "id")
	public Id getId() {
		return id;
	}
	public void setId_name(Id id) {
		this.id = id;
	}
	@XmlElement(name = "property")
	public List<Property> getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}
	@Override
	public String toString() {
		return "Class [name=" + name + ", table=" + table + ", id=" + id + ", propertyList=" + propertyList
				+ "]";
	}
}

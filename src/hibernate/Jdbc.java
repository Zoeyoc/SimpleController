package hibernate;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jdbc")
public class Jdbc {
	private List<Property> propertyList;
	@XmlElement(name = "property")
	public List<Property> getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}
	@Override
	public String toString() {
		return "Jdbc [propertyList=" + propertyList + "]";
	}
}

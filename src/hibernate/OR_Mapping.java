package hibernate;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OR-Mapping")
public class OR_Mapping {
	private Jdbc jdbc;
	private List<OrClass> classList;
	@XmlElement(name = "jdbc")
	public Jdbc getJdbc() {
		return jdbc;
	}
	public void setJdbc(Jdbc jdbc) {
		this.jdbc = jdbc;
	}
	@XmlElement(name = "class")
	public List<OrClass> getClassList() {
		return classList;
	}
	public void setClassList(List<OrClass> classList) {
		this.classList = classList;
	}
	@Override
	public String toString() {
		return "OR_Mapping [jdbc=" + jdbc + ", classList=" + classList + "]";
	}
}

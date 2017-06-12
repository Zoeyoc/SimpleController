package interceptor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "action")
public class LogAction {
	private String name;
	private String stime;
	private String etime;
	private String result;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@XmlElement(name = "s-time")
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	
	@XmlElement(name = "e-time")
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	
	@XmlElement(name = "result")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	

}

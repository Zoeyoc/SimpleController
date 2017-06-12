package interceptor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "log")
public class Log {
	private LogAction logAction;

	public LogAction getLogAction() {
		return logAction;
	}

	public void setLogAction(LogAction logAction) {
		this.logAction = logAction;
	}
	
}

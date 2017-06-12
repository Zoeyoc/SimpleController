package interceptor;


import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionInterceptor {
	private String name;
	private String stime;
	private String etime;
	private String result;
	private Object obj;
	private Method method;

	public ActionInterceptor(String interceptorClazzName, String interceptorClazzMethod) {
		try {
			// 利用反射：由解析得到的类的名称创建对象
			Class<?> classs = Class.forName(interceptorClazzName);
			this.obj = classs.newInstance();
			// 由解析得到的方法名称及参数类型找到对象中对应的方法
			this.method = classs.getDeclaredMethod(interceptorClazzMethod, String.class, String.class, String.class,
					String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//在 Action 执行之前，使用LogWriter 记录 Action 的名称、类型、访问开始时间
	public void before(String name) {
		this.name = name;
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.stime = dateFormat.format(date);
	}
	//在 Action 执行之后记录 Action 访问结束时间，及返回的结果 result
	public void after(String result) {
		try {
			this.result = result;
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.etime = dateFormat.format(date);
			// 调用方法
			this.method.invoke(this.obj, this.name, this.stime, this.etime, this.result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}

package interceptor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class logWriter {
	public void log(String n, String st, String et, String res) {
		try {
			SAXReader reader = new SAXReader();
		
			//用文件路径构造
			String path = "D:\\Log.xml";
			//Document document = reader.read(new File(path));
			
			
			File file = new File(path);
			Document document = reader.read(file);
			
			Element root = document.getRootElement();
			Element action = root.addElement("action");
			Element name = action.addElement("name");
			name.setText(n);
			Element stime = action.addElement("s-time");
			stime.setText(st);
			Element etime = action.addElement("e-time");
			etime.setText(et);
			Element result = action.addElement("result");
			result.setText(res);
			OutputFormat format = OutputFormat.createPrettyPrint();// 创建文件输出的时候，自动缩进的格式
			format.setEncoding("UTF-8");// 设置编码
		
			
			XMLWriter xmlwriter = new XMLWriter(new FileWriter(file),format);
			xmlwriter.write(document);
			// This will print the Document to the current Writer.
			xmlwriter.close();
		} catch (DocumentException ec) {
			ec.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 	
		 

		
	}
}

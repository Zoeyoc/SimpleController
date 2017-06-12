package hibernate;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Configuration {
	private OR_Mapping or_mapping;

	public Configuration() {

		or_mapping = null;
		try {
			File file = new File(Configuration.class.getResource("/or_mapping.xml").getPath());
			JAXBContext jaxbContext = JAXBContext.newInstance(OR_Mapping.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			or_mapping = (OR_Mapping) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public OR_Mapping getOr_mapping() {
		return or_mapping;
	}

	public void setOr_mapping(OR_Mapping or_mapping) {
		this.or_mapping = or_mapping;
	}

}

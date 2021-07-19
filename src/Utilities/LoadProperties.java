package Utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
	
	//The inputs which are required to perform the testing is saved in 'Details.properties' file
	//Those input datas can be accessed using this method
   public static String loadProperties(String filename , String key) throws Throwable {

	    Properties prop = new Properties();

	    InputStream loadFile = null;

	    loadFile = new FileInputStream(filename);
	
	    prop.load(loadFile);

	    String value = prop.getProperty(key);

	    loadFile.close();

	    return value;
	}

}

package com.atmecs.keyword.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.atmecs.keyword.config.Constant;

public class ReadObject {
	    Properties p = new Properties();
	    public Properties getObjectRepository() throws IOException{
	        //Read object repository file
	        InputStream stream = new FileInputStream(Constant.loginData_file);
	        //load all objects
	        p.load(stream);
	         return p;
	    }
	    

}

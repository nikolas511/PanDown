package cn.pjj.factory;

import java.io.IOException;
import java.util.Properties;

public class Factory {
	private Factory(){}
	private static Properties prop = new Properties();
	static{
		try {
			prop.load(Factory.class.getClassLoader().getResourceAsStream("factory.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private static Factory instance = new Factory();
	public static Factory getInstance(){
		return instance;
	}
	public <T> T createImpl(Class<T> clazz){
		String name = clazz.getSimpleName();
		String className = prop.getProperty(name);
		try {
			return (T)Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}

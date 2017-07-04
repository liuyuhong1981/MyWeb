package org.lyh.myweb.util;
/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年3月1日
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtil {
	/**
	 * convert object with XML annotation define to XML String
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertToXmlString(Object obj) {
		// 创建输出流
		StringWriter sw = new StringWriter();
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// 将对象转换成输出流形式的xml
			marshaller.marshal(obj, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * convert object with XML annotation define to XML file
	 * 
	 * @param obj
	 * @param path
	 */
	public static void convertToXmlFile(Object obj, String path) {
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// 将对象转换成输出流形式的xml
			// 创建输出流
			FileWriter fw = null;
			try {
				fw = new FileWriter(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			marshaller.marshal(obj, fw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * convert XML String to XML file
	 * 
	 * @param xmlString
	 * @param path
	 */
	public static void writeStringToXmlFile(String xmlString, String path) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(path);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			bw.write(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				osw.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * convert XML String to object with XML annotation define
	 * 
	 * @param clazz
	 * @param xmlStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			// 进行将Xml转成对象的核心接口
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader sr = new StringReader(xmlStr);
			xmlObject = unmarshaller.unmarshal(sr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

	/**
	 * convert XML file to object with XML annotation define
	 * 
	 * @param clazz
	 * @param xmlPath
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			FileReader fr = null;
			try {
				fr = new FileReader(xmlPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			xmlObject = unmarshaller.unmarshal(fr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

	/**
	 * convert XML document to XML file
	 * 
	 * @param fullPathWithFileName
	 * @param document
	 * @param encoding
	 */
	public static void writeDocumentToFile(String fullPathWithFileName, Document document, String encoding) {
		OutputFormat format = null;
		File file = null;
		XMLWriter writer = null;

		try {
			format = OutputFormat.createPrettyPrint();
			if (encoding == null || encoding.trim().length() == 0) {
				file = new File(fullPathWithFileName);
				writer = new XMLWriter(new FileOutputStream(file));
			} else {
				format.setEncoding(encoding);
				file = new File(fullPathWithFileName);
				writer = new XMLWriter(new FileOutputStream(file), format);
			}
			writer.write(document);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * convert XML file to XML document
	 * 
	 * @param fileFullPath
	 * @return
	 */
	public static Document readDocumentFromFile(String fileFullPath) {
		Document doc = null;
		SAXReader reader = new SAXReader();
		try {
			FileInputStream in = new FileInputStream(fileFullPath);
			doc = reader.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doc;
	}
}

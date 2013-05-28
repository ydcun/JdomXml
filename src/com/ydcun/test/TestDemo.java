package com.ydcun.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class TestDemo {
	private static Document document; // 存放读取的文件
	private static String path = "src/struts.xml"; // 文件存放路径
	//加载文件
	public TestDemo() throws Exception {
		SAXBuilder bulider = new SAXBuilder();
		InputStream inSt = new FileInputStream(path);
		document = bulider.build(inSt);
	}
	//遍历解析文档
	public void xmlParse(){
		
		
		Element root = document.getRootElement();		//获取根节点对象
		List<Element> list = root.getChildren();
		for(Element el: list){
			System.out.println("id="+el.getAttributeValue("id"));
			System.out.println("  name:"+el.getChildText("username"));
			System.out.println("  password:"+el.getChildText("password"));
		}

	}
	//添加节点
	public  void addElement() throws  Exception{
		//创建一个person节点
		Element el = new Element("person");
		el.setAttribute("id","3");
		//创建username节点
		Element elName = new Element("username");
		elName.setText("qiqi");
		///创建password节点
		Element elPassword = new Element("password");
		elPassword.setText("123456");
		//将username，password添加到person节点内
		el.addContent(elName);
		el.addContent(elPassword);
		
		//获取根节点将person节点添加到根节点内
		Element root = document.getRootElement();
		root.addContent(el);
		document.setRootElement(root);
		
		//将添加的保存到文件中
		XMLOutputter out = new XMLOutputter();
		out.output(document, new FileOutputStream(path));
		
	}
	//删除节点
	public void deleteElement(int id) throws Exception{
		Element root = document.getRootElement();
		List<Element> list = root.getChildren();
		for(Element el : list){
			if(el.getAttributeValue("id").equals(id+"")){
				root.removeContent(el);	//将符合条件的节点删除
			}
		}
		XMLOutputter out = new XMLOutputter();
		out.output(document, new FileOutputStream(path));
	
	}
	public static void main(String[] args) throws Exception {
		TestDemo td = new TestDemo();
//		td.addElement();//添加节点
		td.xmlParse();//便利xml文件
//		td.deleteElement(3);//删除节点
	}
}

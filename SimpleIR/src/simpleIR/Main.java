package simpleIR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String str;
		StringBuilder sb = new StringBuilder();
		String[] s = new String[5];
		String[] food = {"떡","라면","아이스크림","초밥","파스타"};

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document d = docBuilder.newDocument();
		Element docs = d.createElement("docs");
		d.appendChild(docs);
		
		for (int n = 0; n < food.length; n++) {
			File f = new File("C:\\Users\\bhs12\\Desktop\\2주차 실습 html\\" + food[n] + ".html");
			try {
				if (f.exists()) {
				
					BufferedReader br = new BufferedReader(new FileReader(f));

					str = br.readLine();
					while(str!=null) {

						s[n] = getTextfromHtml(str);
						str = br.readLine();
						
					}
					
					//System.out.println(str);
					br.close();
					//System.out.println(sb);

					Element doc = d.createElement("doc");
					docs.appendChild(doc);
					doc.setAttribute("id", Integer.toString(n));

					
					Element title = d.createElement("title");
					title.appendChild(d.createTextNode(food[n]));
					docs.appendChild(title);

					Element body = d.createElement("body");
					body.appendChild(d.createTextNode(s[n]));
					docs.appendChild(body);
				

				} else {
					System.out.println("파일을 찾을 수 없습니다");
				}
				// 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// XML 형식으로
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		DOMSource source = new DOMSource(d);
		StreamResult result = new StreamResult(
				new FileOutputStream(new File("C:\\Users\\bhs12\\Desktop\\2주차 실습 html\\food.xml")));
		transformer.transform(source, result);

	}

	static String getTextfromHtml(String html) {

		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		return doc.select("p").text();
//		Document doc;
//		doc = Jsoup.connect(html).get();
//
//	    Elements link = doc.select("div.my.value");
//	    doc=Jsoup.parse(link.html());
//	    link =doc.select("p");
//	    String linkText = link.text();

	}

}

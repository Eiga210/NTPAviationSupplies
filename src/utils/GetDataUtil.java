package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Air;

public class GetDataUtil {

	public static List<String> getData(String dataPath){
	     ArrayList<String> list = new ArrayList<String>();
	     try (FileInputStream fileInputStream = new FileInputStream(new File(dataPath));
	             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
	             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
	         String content = "";
	         while (bufferedReader.ready()) {
	             content = bufferedReader.readLine();
	             System.out.println(content);  
	             list.add(content);
	         }
	         if (!list.isEmpty()) {
	             list.remove(0);
	         }
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     return list;
	 }
	 public static List<Air> getAirDataFromJson(String filePath) {
	        List<Air> airList = new ArrayList<>();
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            Air[] airDataArray = objectMapper.readValue(new File(filePath), Air[].class);
	            for (Air air : airDataArray) {
	                airList.add(air);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("JSON檔案讀取失敗");
	        }
	        return airList;
	    }
	  public static List<Air> getAirDataFromXml(String filePath) {
	        List<Air> airList = new ArrayList<>();
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(new File(filePath));
	            NodeList nodeList = document.getElementsByTagName("Air");
	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node node = nodeList.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element element = (Element) node;
	                    Air air = new Air();
	                    air.setSeqno(getIntValue(element, "Seqno"));
	                    air.setDistrict(getTextContent(element, "District"));
	                    air.setYear(getIntValue(element, "Year"));
	                    air.setType(getTextContent(element, "Type"));
	                    air.setName(getTextContent(element, "Name"));
	                    air.setArea_length(getTextContent(element, "AreaLength"));
	                    air.setManager(getTextContent(element, "Manager"));
	                    airList.add(air);
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("XML 文件解析失敗: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return airList;
	    }
	    private static String getTextContent(Element element, String tagName) {
	        NodeList nl = element.getElementsByTagName(tagName);
	        if (nl.getLength() > 0) {
	            Node node = nl.item(0);
	            return node.getTextContent().trim();
	        }
	        return ""; 
	    }
	    private static int getIntValue(Element element, String tagName) {
	        String content = getTextContent(element, tagName);
	        try {
	            return Integer.parseInt(content);
	        } catch (NumberFormatException e) {
	            return 0; 
	        }
	    }
}

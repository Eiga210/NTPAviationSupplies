package ExportData;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.Gson;

import model.Air;
import utils.JDBCutil;

public class DataExport {
	public static void exportToCSV()  {
		 String sql = "SELECT * FROM Air";
		try(Connection connection = JDBCutil.getConnection();
			Statement	statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(sql);
			FileWriter writer = new FileWriter("output.csv");	) {
		  writer.append("ID, District, Year, Type, Name, Area Length, Manager\n"); 
		  while (resultSet.next()) {
	            writer.append(resultSet.getInt("seqno") + ", ");
	            writer.append(resultSet.getString("district") + ", ");
	            writer.append(resultSet.getInt("year") + ", ");
	            writer.append(resultSet.getString("type") + ", ");
	            writer.append(resultSet.getString("name") + ", ");
	            writer.append(resultSet.getString("area_length") + ", ");
	            writer.append(resultSet.getString("manager") + "\n");
	        }
		  System.out.println("CSV 資料已成功輸出！");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 System.out.println("寫入 CSV 檔案時發生錯誤: " + e.getMessage());
			e.printStackTrace();
		}
	}
	 public static void exportToJSON() {
	        String sql = "SELECT * FROM Air";
	        try (Connection connection = JDBCutil.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)) {
	        		List<Air> airList = new ArrayList<>();
	        		while (resultSet.next()) {
	                Air air = new Air(
	                        resultSet.getInt("seqno"),
	                        resultSet.getString("district"),
	                        resultSet.getInt("year"),
	                        resultSet.getString("type"),
	                        resultSet.getString("name"),
	                        resultSet.getString("area_length"),
	                        resultSet.getString("manager")
	                );
	                airList.add(air);
	            }
	            Gson gson = new Gson();
	            try (FileWriter writer = new FileWriter("output.json")) {
	                gson.toJson(airList, writer);
	                System.out.println("JSON 資料已成功輸出！");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.out.println("寫入 JSON 檔案時發生錯誤: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	 public static void exportToXML() {
	        String sql = "SELECT * FROM Air";
	        try (Connection connection = JDBCutil.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)) {
	            List<Air> airList = new ArrayList<>();
	            while (resultSet.next()) {
	                Air air = new Air(
	                        resultSet.getInt("seqno"),
	                        resultSet.getString("district"),
	                        resultSet.getInt("year"),
	                        resultSet.getString("type"),
	                        resultSet.getString("name"),
	                        resultSet.getString("area_length"),
	                        resultSet.getString("manager")
	                );
	                airList.add(air);
	            }
	            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	            Document document = documentBuilder.newDocument();
	            Element root = document.createElement("AirQualityData");
	            document.appendChild(root);
	            for (Air air : airList) {
	                Element airElement = document.createElement("Air");
	                Element seqno = document.createElement("Seqno");
	                seqno.appendChild(document.createTextNode(String.valueOf(air.getSeqno())));
	                airElement.appendChild(seqno);
	                Element district = document.createElement("District");
	                district.appendChild(document.createTextNode(air.getDistrict()));
	                airElement.appendChild(district);
	                Element year = document.createElement("Year");
	                year.appendChild(document.createTextNode(String.valueOf(air.getYear())));
	                airElement.appendChild(year);
	                Element type = document.createElement("Type");
	                type.appendChild(document.createTextNode(air.getType()));
	                airElement.appendChild(type);
	                Element name = document.createElement("Name");
	                name.appendChild(document.createTextNode(air.getName()));
	                airElement.appendChild(name);
	                Element areaLength = document.createElement("AreaLength");
	                areaLength.appendChild(document.createTextNode(air.getArea_length()));
	                airElement.appendChild(areaLength);
	                Element manager = document.createElement("Manager");
	                manager.appendChild(document.createTextNode(air.getManager()));
	                airElement.appendChild(manager);
	                root.appendChild(airElement);
	            }
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(document);
	            StreamResult result = new StreamResult(new FileWriter("output.xml"));
	            transformer.transform(source, result);
	            System.out.println("XML資料已成功輸出！");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.out.println("寫入 XML 檔案時發生錯誤: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
}

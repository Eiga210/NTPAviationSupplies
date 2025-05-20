package lab;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Dao.WindowDemo;
import Service.AirService;
import model.Air;
import utils.GetDataUtil;

public class DemoAir {
	private static  AirService airService = new AirService();
	
	public static void main(String[] args) {
		
		 String[] options = {"CSV", "JSON","XML"};
	        int choice = JOptionPane.showOptionDialog(
	            null,
	            "請選擇載入的檔案格式",
	            "選擇輸入檔案格式",
	            JOptionPane.DEFAULT_OPTION,
	            JOptionPane.INFORMATION_MESSAGE,
	            null,
	            options,
	            options[0] 
	        );
	        String filePath = "C:\\Users\\xin\\Downloads\\新北市空氣品質淨化區";  
	        final List<Air> airList;
	        if (choice == 0) {  
	            filePath += ".csv";
	            List<String> stringList = GetDataUtil.getData(filePath);
	            airList = airService.getAirData(stringList);
	        } else if (choice == 1) { 
	            filePath += ".json";
	            airList = GetDataUtil.getAirDataFromJson(filePath);
	        } else if (choice == 2) {
	        	filePath += ".xml";
	            airList = GetDataUtil.getAirDataFromXml(filePath);
			}
	        else {
	            airList = null;
	        }
	        if (airList != null) {
	            SwingUtilities.invokeLater(() -> {
	                WindowDemo window = new WindowDemo(airList);
	                window.loadData(airList);
	            });
	        } else {
	            JOptionPane.showMessageDialog(null, "資料加載失敗，請檢查文件格式或路徑是否正確", "錯誤", JOptionPane.ERROR_MESSAGE);
	        }
	}
}
		
	
	
	
	
	    


package Dao;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class DistrictSelection{
	private String district;
	public static DistrictSelection selectDistrict() {
		  String[] districts = {
			        "板橋區", "三重區", "中和區", "永和區", "新莊區", "新店區", "土城區", "蘆洲區",
			        "樹林區", "汐止區", "鶯歌區", "三峽區", "淡水區", "瑞芳區", "五股區", "泰山區", 
			        "林口區", "深坑區", "石碇區", "坪林區", "三芝區", "石門區", "八里區", "平溪區", 
			        "雙溪區", "貢寮區", "金山區", "萬里區", "烏來區"
			    };
			    JComboBox<String> districtComboBox = new JComboBox<>(districts);
			    int option = JOptionPane.showConfirmDialog(
			            null, districtComboBox, "請選擇新北市行政區域", JOptionPane.OK_CANCEL_OPTION);
			    if (option == JOptionPane.OK_OPTION) {
			        String selectedDistrict = (String) districtComboBox.getSelectedItem();
			        DistrictSelection disSelect = new DistrictSelection();
		            disSelect.setDistrict(selectedDistrict);
			        return disSelect;
			    } else {
			        return null; 
			    }
	}
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
}
package Dao;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BaseTypeSelection {
	 private String type;
 	public static BaseTypeSelection selectTypes() {
 	String[]types= {
 		"環保公園","空地綠化", "都會區道路綠化", "垃圾場復育綠化",
 		"裸露地綠化", "道路綠帶", "空地(社區)綠美化"
 	} ;
 	JComboBox<String>typeComboBox=new JComboBox<>(types);
 	int option =JOptionPane.showConfirmDialog(null, typeComboBox, "請選擇基地類別",JOptionPane.OK_CANCEL_OPTION);
 	if(option==JOptionPane.OK_OPTION) {
 		String selectedType=(String) typeComboBox.getSelectedItem();
 		BaseTypeSelection baseTypeSelection=new BaseTypeSelection();
 		baseTypeSelection.setType(selectedType);
 		return baseTypeSelection;
 	}else {
			return null;
		}
 }
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
}

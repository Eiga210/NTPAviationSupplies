package Dao;

import javax.swing.JOptionPane;

public class DataViewer {
	public static String inputWithValidation(String message, String existingValue,boolean allowEmpty) {
	    while (true) {
	        String input = JOptionPane.showInputDialog(message, existingValue);
	        if (input == null) {
	            JOptionPane.showMessageDialog(null, "新增操作已取消！");
	            return null;
	        }
	        if (input.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "輸入不能為空！");
	            continue;
	        }
	        return input;
	    }
	}
}
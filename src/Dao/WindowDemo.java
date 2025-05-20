package Dao;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import ExportData.DataExport;
import model.Air;

public class WindowDemo {
	private static AirDao airDao = new AirDao();
	 private JFrame frame;
	 private JMenuBar menuBar;
	    private JMenu menu;
	    private JMenuItem itemAdd, itemViewAll, itemViewSpecific, itemUpdate, itemDelete, itemExport, itemExit;
	    private List<Air> airList;
	    private JTextArea textArea;
	    public WindowDemo(List<Air> airList) {
	    	//this.airList = airList;
	        frame = new JFrame("新北市空氣品質管理系統");
	        frame.setSize(766, 352);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel() {
	        	@Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\xin\\Downloads\\New_Taipei.jpeg"); 
	                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);  
	            }
	        };
	        panel.setLayout(new BorderLayout());
	        menuBar = new JMenuBar();
	        menu = new JMenu("操作");
	        itemAdd = new JMenuItem("新增資料");
	        itemViewAll = new JMenuItem("查詢全部資料");
	        itemViewSpecific = new JMenuItem("查詢單筆資料");
	        itemUpdate = new JMenuItem("更新資料");
	        itemDelete = new JMenuItem("刪除資料");
	        itemExport = new JMenuItem("輸出資料");
	        itemExit = new JMenuItem("退出");
	        menu.add(itemAdd);
	        menu.add(itemViewAll);
	        menu.add(itemViewSpecific);
	        menu.add(itemUpdate);
	        menu.add(itemDelete);
	        menu.add(itemExport);
	        menu.add(itemExit);
	        menuBar.add(menu);
	        frame.setJMenuBar(menuBar);
	        textArea = new JTextArea();
	        textArea.setEditable(false);
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        JScrollPane scrollPane = new JScrollPane(textArea);
	        frame.add(scrollPane, BorderLayout.CENTER);
	        frame.setContentPane(panel);
	        frame.setVisible(true);
	        itemAdd.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                addData();
	            }
	        });
	        itemViewAll.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	viewAllData();
	            }
	        });
	        itemViewSpecific.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	viewSpecificData();
	            }
	        });
	        itemUpdate.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	updateData();
	            }
	        });
	        itemDelete.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	deleteData();
	            }
	        });
	        itemExport.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	exportData();
	            }
	        });
	        itemExit.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });
	    }
	  //載入
		public void loadData(List<Air> airData) {
			for (Air air:airData) {
				Air existingair = airDao.findAirByUniqueIdentifier(air);
				if (existingair == null) {
		            airDao.saveAir( air);  
		        } else {
		           System.out.println(existingair);
		        }
			}
		}
		//新增
		public static void addData () {
			 DistrictSelection selectedAir = DistrictSelection.selectDistrict(); 
			  if (selectedAir == null || selectedAir.getDistrict() == null || selectedAir.getDistrict().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "未選擇有效的行政區域！");
		            return; 
		        }
		    Integer year = getYearFromInputer(null);
		    if (year == null) return;
		    BaseTypeSelection selectTypes = BaseTypeSelection.selectTypes();
		    if(selectTypes==null||selectTypes.getType()==null||selectTypes.getType().isEmpty()) {
		    	JOptionPane.showMessageDialog(null, "未選擇有效的類別！");
		    	return;
		    }
		    String name = InputerName( null);
		    if (name == null) return; 
		    String areaLength = InputerareaLength(null);
		    if (areaLength == null) return;
		    String manager = InputerManager(null);
		    if (manager == null) return;
		    Air newAir = new Air();
		    newAir.setDistrict(selectedAir.getDistrict());
		    newAir.setYear(year);
		    newAir.setType(selectTypes.getType());
		    newAir.setName(name);
		    newAir.setArea_length(areaLength);
		    newAir.setManager(manager);
		    airDao.saveAir(newAir);  
		    JOptionPane.showMessageDialog(null, "資料已新增！");
		}
		//年份設定
			private static Integer getYearFromInputer(Integer existingYear) {
				return AirDao.getYear("請輸入設置年度(民國)",  existingYear != null ? existingYear.toString() : "");
			    
			}
			//基地名稱設定
			private static String InputerName(String existingName) {
				 return DataViewer.inputWithValidation("請輸入基地名稱", existingName, false);
			}
			//面積設定
			private static String InputerareaLength(String existingareaLength) {
				 String inputareaLength= DataViewer.inputWithValidation("請輸入基地面積或長度(幾公頃)", existingareaLength != null ? existingareaLength : "", false);
				 if (inputareaLength == null) {
				        return null;
				    }
				 while (true) {
					 inputareaLength = inputareaLength.replace("公頃", "").trim();
			        if (inputareaLength.matches(".*[\u4e00-\u9fa5].*")) {
			            JOptionPane.showMessageDialog(null, "請輸入數字！");
			            inputareaLength = DataViewer.inputWithValidation("請輸入基地面積或長度(幾公頃)", existingareaLength != null ? existingareaLength : "", false);
			            continue;
			        }
			        try {
			            Double.parseDouble(inputareaLength); 
			        } catch (NumberFormatException e) {
			            JOptionPane.showMessageDialog(null, "請輸入有效的數字格式！");
			            inputareaLength = DataViewer.inputWithValidation("請輸入基地面積或長度(幾公頃)", existingareaLength != null ? existingareaLength : "", false);
			            continue;
			        }
			        return inputareaLength + "公頃";
			    }
			}
			//管理單位設定
			private static String InputerManager(String existingManager) {
				 return DataViewer.inputWithValidation("請輸入管理單位", existingManager, false);
			}
			//更新資料
			 public static void updateData() {
				 String inputId = JOptionPane.showInputDialog("請輸入要更新的資料 ID");
				 if (inputId == null || inputId.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "ID 不能為空");
				        return;
				    }
				 try {
				    	int id = Integer.parseInt(inputId);
				    	 Air existingAir = airDao.findAirById(id);
				    	 if(existingAir == null) {
				    		 JOptionPane.showMessageDialog(null, "查無此ID的資料");
				    		 return;
				    	 }
				
					 DistrictSelection selectedAir = DistrictSelection.selectDistrict(); 
					 if (selectedAir == null || selectedAir.getDistrict() == null || selectedAir.getDistrict().isEmpty()) {
			            JOptionPane.showMessageDialog(null, "未選擇有效的行政區域！");
			            return; 
			            } 
					 Integer newYear =  getYearFromInputer(existingAir.getYear());
					 if (newYear== null) return; 
			         BaseTypeSelection baseTypeSelection=BaseTypeSelection.selectTypes();
			         if(baseTypeSelection==null||baseTypeSelection.getType()==null||baseTypeSelection.getType().isEmpty()) {
			        	JOptionPane.showMessageDialog(null, "未選擇有效的類別！");
			 	    	return;
			         }
						 String newName = InputerName(existingAir.getName());
						 if (newName== null) return; 
						 String newAreaLength = InputerareaLength(existingAir.getArea_length());
						 if (newAreaLength== null) return; 
						 String newManager = InputerManager(existingAir.getManager());
						 if (newManager== null) return; 
						 existingAir.setDistrict(selectedAir.getDistrict());
						 existingAir.setYear(newYear);
						 existingAir.setType(baseTypeSelection.getType());
						 existingAir.setName(newName);
						 existingAir.setArea_length(newAreaLength);
						 existingAir.setManager(newManager);
						 airDao.updateAir(id, existingAir);
						 JOptionPane.showMessageDialog(null, "資料已更新！");
					 
			            } catch (NumberFormatException e) {
			                JOptionPane.showMessageDialog(null, "請輸入有效的數字或格式");
			            }
				  }
			//查詢全部資料
			 public static void viewAllData() {
				 List<Air> allData = airDao.findAllAir(); 
				 if (allData == null || allData.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "查無資料!");
				        return;
				    }
			        StringBuilder dataStr = new StringBuilder();
			        for (Air air : allData) {
			            dataStr.append(air.toString()).append("\n");
			        }
			        JFrame dataFrame = new JFrame("查詢全部資料");
			        JTextArea textArea = new JTextArea(dataStr.toString());
			        textArea.setEditable(false);
			        textArea.setLineWrap(true);
			        textArea.setWrapStyleWord(true);
			        JScrollPane scrollPane = new JScrollPane(textArea);
			        dataFrame.add(scrollPane);
			        dataFrame.setSize(600, 400);
			        dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        dataFrame.setVisible(true);
			    }
			 //查詢單筆
			 public static void viewSpecificData() {
				    // 提供查詢方式選擇
				    String[] options = { "根據 ID 查詢", "根據地區查詢", "根據基地類別查詢" };
				    int choice = JOptionPane.showOptionDialog(null, "請選擇查詢方式", "查詢方式",
				            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				    if (choice == 0) { // 根據 ID 查詢
				        String inputId = JOptionPane.showInputDialog("請輸入要查詢的資料 ID");
				        if (inputId != null && !inputId.isEmpty()) {
				            try {
				                int id = Integer.parseInt(inputId);
				                Air air = airDao.findAirById(id);
				                if (air != null) {
				                    JOptionPane.showMessageDialog(null, air.toString());
				                } else {
				                    JOptionPane.showMessageDialog(null, "查無資料");
				                }
				            } catch (NumberFormatException e) {
				                JOptionPane.showMessageDialog(null, "請輸入有效的 ID");
				            }
				        }
				    } else if (choice == 1) { 
				    	 DistrictSelection selectedAir = DistrictSelection.selectDistrict(); 
						  if (selectedAir == null || selectedAir.getDistrict() == null || selectedAir.getDistrict().isEmpty()) {
					            JOptionPane.showMessageDialog(null, "未選擇有效的行政區域！");
					            return; // 如果沒有選擇有效的行政區，則退出方法
					        }
						  String district = selectedAir.getDistrict();
						  List<Air> airList = airDao.findAirByDistrict(district);
						  if (airList != null && !airList.isEmpty()) {
					            StringBuilder result = new StringBuilder("查詢結果:\n");
					            for (Air air : airList) {
					                result.append(air.toString()).append("\n");
					            }
					            JOptionPane.showMessageDialog(null, result.toString());
					        } else {
					            JOptionPane.showMessageDialog(null, "查無資料");
					        }
				    } else if (choice == 2) { 
				    	 BaseTypeSelection selectTypes = BaseTypeSelection.selectTypes();
				 	    if(selectTypes==null||selectTypes.getType()==null||selectTypes.getType().isEmpty()) {
				 	    	JOptionPane.showMessageDialog(null, "未選擇有效的類別！");
				 	    	return;
				 	    }
				 	    String type=selectTypes.getType();
				            List<Air> airList = airDao.findAirByType(type);  
				            if (airList != null && !airList.isEmpty()) {
				                StringBuilder result = new StringBuilder("查詢結果:\n");
				                for (Air air : airList) {
				                    result.append(air.toString()).append("\n");
				                }
				                JOptionPane.showMessageDialog(null, result.toString());
				            } else {
				                JOptionPane.showMessageDialog(null, "查無資料");
				            }
				        }
				    }
			 //刪除
			 public static void deleteData() {
				 String inputId = JOptionPane.showInputDialog("請輸入要刪除的資料 ID");
			        if (inputId != null && !inputId.isEmpty()) {
			            try {
			                int id = Integer.parseInt(inputId);
			                Air air = airDao.findAirById(id);
			                if (air != null) {
			                    airDao.deleteAirId(id);  // 刪除資料
			                    JOptionPane.showMessageDialog(null, "資料已刪除！");
			                } else {
			                    JOptionPane.showMessageDialog(null, "查無此ID的資料");
			                }
			            } catch (NumberFormatException e) {
			                JOptionPane.showMessageDialog(null, "ID格式不正確");
			            }
			        }
			 }
		//輸出資料	 
		 public static void exportData() {
			 JOptionPane.showMessageDialog(null, "選擇導出格式：");
			 String[] options = {"CSV", "JSON", "XML","返回"};
			 int choice = JOptionPane.showOptionDialog(null, "請選擇輸出格式", "匯出資料", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			 if (choice == 0) {
				 exportToCSV();
			 } else if (choice == 1) {
		         exportToJSON(); 
			 } else if (choice == 2) {
		         exportToXML(); 
		     } else {
		         JOptionPane.showMessageDialog(null, "取消");
		     }
		 }
		 public static void exportToCSV() {
			 DataExport.exportToCSV();
			    	JOptionPane.showMessageDialog(null, "資料已成功匯出為CSV格式！");
		 }
		 public static void exportToJSON() {
		         DataExport.exportToJSON();
		         JOptionPane.showMessageDialog(null, "資料已成功匯出為JSON格式！");
		 }
		 public static void exportToXML() {
	         DataExport.exportToXML();
	         JOptionPane.showMessageDialog(null, "資料已成功匯出為XML格式！");
	 }
		}
	   
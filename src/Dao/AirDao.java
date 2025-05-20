package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Air;
import utils.JDBCutil;
public class AirDao {

    // 查找特定 ID 的資料
	public Air findAirById(int id) {
	    String sql = "SELECT * FROM Air WHERE seqno = ?";
	    Air air = null;
	    try ( Connection connection = JDBCutil.getConnection();
	    	PreparedStatement preparedStatement = connection.prepareStatement(sql);){
	        preparedStatement.setInt(1, id);
	        try(ResultSet resultSet = preparedStatement.executeQuery();){
	        if (resultSet.next()) {
	            air = new Air();
	            air.setSeqno(resultSet.getInt("seqno"));
	            air.setDistrict(resultSet.getString("district"));
	            air.setYear(resultSet.getInt("year"));
	            air.setType(resultSet.getString("type"));
	            air.setName(resultSet.getString("name"));
	            air.setArea_length(resultSet.getString("area_length"));
	            air.setManager(resultSet.getString("manager"));
	            }
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return air;
	}

	 public void saveAir(Air air) {
		//創建
		String sql="INSERT INTO Air(district,year,type,name,area_length,manager)"
				+" VALUES(?,?,?,?,?,?)";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, air.getDistrict());
			preparedStatement.setInt(2, air.getYear());
			preparedStatement.setString(3, air.getType());
			preparedStatement.setString(4, air.getName());
			preparedStatement.setString(5, air.getArea_length());
			preparedStatement.setString(6, air.getManager());
			preparedStatement.execute();
			System.out.println("新增資料");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement);
		}
	}
	
	//查詢全部
	public List<Air> findAllAir()  {
        String sql = "SELECT * FROM Air";
        Connection connection = JDBCutil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Air> airList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	Air air = new Air();
            	 air.setSeqno(resultSet.getInt("seqno"));
            	    air.setDistrict(resultSet.getString("district"));
            	    air.setYear(resultSet.getInt("year"));
            	    air.setType(resultSet.getString("type"));
            	    air.setName(resultSet.getString("name"));
            	    air.setArea_length(resultSet.getString("area_length"));
            	    air.setManager(resultSet.getString("manager"));
            	    airList.add(air); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCutil.closeResource(connection, preparedStatement, resultSet);
        }
		return airList;
    }
	//查詢單筆
	public Air readAir(Integer id) {
			String sql="SELECT * FROM Air WHERE seqno=?";
			 Air air = null;
			Connection connection = JDBCutil.getConnection();
			PreparedStatement preparedStatement=null;
			ResultSet resultSet=null;
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				resultSet=preparedStatement.executeQuery();
				resultSet.next();
				System.out.println(resultSet.getString(1)+","+resultSet.getString("district")+","+resultSet.getInt("year")+","+resultSet.getString("type")+","
				+resultSet.getString("name")+","+resultSet.getString("area_length")+","+resultSet.getString("manager"));
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCutil.closeResource(connection, preparedStatement,resultSet);
			}
			 return air;
		}
	
	public Air findAirByUniqueIdentifier(Air air) {
	    String district = air.getDistrict();
	    String name = air.getName();
	    String sql = "SELECT * FROM Air WHERE district = ? AND name = ?";
	    try (Connection connection = JDBCutil.getConnection();
	    	PreparedStatement prepareStatement = connection.prepareStatement(sql)){
	        prepareStatement.setString(1, district);
	        prepareStatement.setString(2, name);
	       try (ResultSet resultSet = prepareStatement.executeQuery()){
	        if (resultSet.next()) {
	            Air existingAir = new Air();
	            existingAir.setDistrict(resultSet.getString("district"));
	            existingAir.setName(resultSet.getString("name"));
	            existingAir.setYear(resultSet.getInt("year"));
                existingAir.setType(resultSet.getString("type"));
                existingAir.setArea_length(resultSet.getString("area_length"));
                existingAir.setManager(resultSet.getString("manager"));
	            return existingAir;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	//查詢單筆資料(種類)
	public List<Air> findAirByType(String type) {
	    List<Air> airList = new ArrayList<>();
	    String sql = "SELECT * FROM air WHERE type = ?";
	    try (Connection connection = JDBCutil.getConnection();
	         PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
	    	prepareStatement.setString(1, type); 
	        ResultSet resultSet = prepareStatement.executeQuery();
	        while (resultSet.next()) {
	            Air air = new Air();
	            air.setSeqno(resultSet.getInt("seqno"));
	            air.setDistrict(resultSet.getString("district"));
	            air.setYear(resultSet.getInt("year"));
	            air.setType(resultSet.getString("type"));
	            air.setName(resultSet.getString("name"));
	            air.setArea_length(resultSet.getString("area_length"));
	            air.setManager(resultSet.getString("manager"));
	            airList.add(air); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	    return airList; 
	}
	//查詢單筆資料(行政區)
	public List<Air> findAirByDistrict(String district) {
	    List<Air> airList = new ArrayList<>();
	    String sql = "SELECT * FROM Air WHERE district = ?";
	    try (Connection connection = JDBCutil.getConnection();
	         PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
	    	prepareStatement.setString(1, district);  
	        ResultSet resultSet = prepareStatement.executeQuery();
	        while (resultSet.next()) {
	            Air air = new Air();
	            air.setSeqno(resultSet.getInt("seqno"));
	            air.setDistrict(resultSet.getString("district"));
	            air.setYear(resultSet.getInt("year"));
	            air.setType(resultSet.getString("type"));
	            air.setName(resultSet.getString("name"));
	            air.setArea_length(resultSet.getString("area_length"));
	            air.setManager(resultSet.getString("manager"));
	            airList.add(air); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	    return airList;
	}

	//年分
	public static Integer getYear(String message, String existingValue) {
	 while (true) {
	        String inputYear = JOptionPane.showInputDialog(message,existingValue);
	        if (inputYear == null) {
	            JOptionPane.showMessageDialog(null, "新增操作已取消！");
	            return null;
	        }
	        if (inputYear.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "年度不能為空！");
	            continue;
	        }
	        try {
	            int year = Integer.parseInt(inputYear);
	            if (year < 0) {
	                JOptionPane.showMessageDialog(null, "請輸入正整數的年度！");
	            } else {
	                return year;  
	            }
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "請輸入有效的數字格式的年度！");
	        }
	    }
	}
	//更新
		public void updateAir(Integer id,Air air) {
			String sql= "UPDATE Air SET district = ?, year = ?, type = ? , name =?, area_length = ?, manager = ? WHERE seqno = ?";
			Connection connection = JDBCutil.getConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, air.getDistrict());
				preparedStatement.setInt(2, air.getYear());
				preparedStatement.setString(3, air.getType());
				preparedStatement.setString(4, air.getName());
				preparedStatement.setString(5, air.getArea_length());
				preparedStatement.setString(6, air.getManager());
				preparedStatement.setInt(7, id);
				int rowsUpdated = preparedStatement.executeUpdate();
		        if (rowsUpdated > 0) {
		            System.out.println("更新資料成功！");
		        } else {
		            System.out.println("未找到要更新的資料，更新未生效！");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("更新資料時發生錯誤：" + e.getMessage());
		    }
		}
		
		//刪除
		public void deleteAirId(Integer ID) {
			String sql="DELETE FROM Air WHERE seqno=?";
			Connection connection = JDBCutil.getConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, ID);
				int row=preparedStatement.executeUpdate();
				System.out.println("刪除"+row+"筆");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCutil.closeResource(connection, preparedStatement);
			}
		}

}
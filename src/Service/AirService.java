package Service;

import java.util.ArrayList;
import java.util.List;
import model.Air;

public class AirService {
	//private static AirDao airDao = new AirDao();
	public List<Air> getAirData(List<String> dataList){
		ArrayList<Air> airList = new ArrayList<>();
		for(int i=0;i<dataList.size();i++) {
			String[]breaths=dataList.get(i).split(",");
			Air air = new Air();
			air.setDistrict(breaths[1]);
			air.setYear(Integer.parseInt(breaths[2]));
			air.setType(breaths[3]);
			air.setName(breaths[4]);
			air.setArea_length(breaths[5]);
			air.setManager(breaths[6]);
			airList.add(air);
		}
		return airList;
	}
}

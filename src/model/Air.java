package model;

public class Air {

	private Integer seqno;
	private String district;
	private Integer year;
	private String type;
	private String name;
	private String area_length;
	private String manager;
	
	public Air() {
        this.seqno = 0;
        this.district = "";
        this.year = 0;
        this.type = "";
        this.name = "";
        this.area_length = "";
        this.manager = "";
    }
	public Air(String[] dataParts) {
        if (dataParts != null && dataParts.length >= 7) {
            try {
                this.seqno = Integer.parseInt(dataParts[0].trim());
                this.district = dataParts[1].trim();
                this.year = Integer.parseInt(dataParts[2].trim());
                this.type = dataParts[3].trim();
                this.name = dataParts[4].trim();
                this.area_length = dataParts[5].trim();
                this.manager = dataParts[6].trim();
            } catch (NumberFormatException e) {
                System.out.println("Error parsing data: " + e.getMessage());
            }
        }
    }
	
	public Air(Integer seqno, String district, Integer year, String type, String name, String area_length, String manager) {
		this.seqno = seqno;
        this.district = district;
        this.year = year;
        this.type = type;
        this.name = name;
        this.area_length = area_length;
        this.manager = manager;
	}
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public void setYearInROCMinguo(int year) {
        this.year = year + 1911; 
    }
    public int getYearInROC() {
        return this.year - 1911; 
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea_length() {
		return area_length;
	}
	public void setArea_length(String area_length) {
		this.area_length = area_length;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return seqno +","+district +","+ year +","+type +","+name +","+area_length +","+ manager;
	}

}

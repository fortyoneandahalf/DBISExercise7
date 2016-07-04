package com.dbExercise7.data;

public class RegionId {

	private int regionId;
	private int landId;
	private String name;
	
	public RegionId(int regionId, int landId, String name) {
		super();
		this.regionId = regionId;
		this.landId = landId;
		this.name = name;
	}
	
	public int getRegionId() {
		return regionId;
	}
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public int getLandId() {
		return landId;
	}
	
	public void setLandId(int landId) {
		this.landId = landId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean save(){
		return true;
	}
	
}

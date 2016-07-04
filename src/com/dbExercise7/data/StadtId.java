package com.dbExercise7.data;

public class StadtId {
	private int stadtId;
	private int regionId;
	private String name;
	
	public StadtId(int stadtId, int regionId, String name) {
		super();
		this.stadtId = stadtId;
		this.regionId = regionId;
		this.name = name;
	}
	
	public int getStadtId() {
		return stadtId;
	}
	
	public void setStadtId(int stadtId) {
		this.stadtId = stadtId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRegionId() {
		return regionId;
	}
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public boolean save(){
		return true;
	}
}

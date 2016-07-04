package com.dbExercise7.data;

public class LandId {
	
	private int landId;
	private String name;
	
	public LandId(int landId, String name) {
		super();
		this.landId = landId;
		this.name = name;
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

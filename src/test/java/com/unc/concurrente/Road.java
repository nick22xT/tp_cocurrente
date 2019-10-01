package com.unc.concurrente;

import java.util.ArrayList;
import java.util.List;

public class Road {
	
	private Integer invariantNumber;
	private List<String> road;
	
	public Road() {
		road = new ArrayList<>();
	}

	public List<String> getRoad() {
		return road;
	}

	public void setRoad(List<String> road) {
		this.road = road;
	}

	public Integer getInvariantNumber() {
		return invariantNumber;
	}

	public void setInvariantNumber(Integer invariantNumber) {
		this.invariantNumber = invariantNumber;
	}

}

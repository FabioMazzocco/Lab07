package it.polito.tdp.poweroutages.model;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	Set<PowerOutage> list;
	PowerOutage first;
	PowerOutage lastInsert;
	int points;
	int hours; 
	
	public Solution() {
		this.list = new HashSet<PowerOutage>();
		this.first = null;
		this.points = 0;
		this.hours = 0;
	}
	
	public Solution(Set<PowerOutage> list, PowerOutage first, PowerOutage lastInsert, int points, int hours) {
		super();
		this.list = list;
		this.first = first;
		this.lastInsert = lastInsert;
		this.points = points;
		this.hours = hours;
	}

	public boolean add(PowerOutage a) {
		if(!list.contains(a)) {
			if(list.size()==0)
				setFirst(a);
			list.add(a);
			hours += a.getHours();
			points += a.getCustomers();
			return true;
		}
		return false;
	}

	public boolean remove(PowerOutage a) {
		if(list.contains(a)) {
			list.remove(a);
			if(list.size()==0)
				first = null;
			points -= a.getCustomers();
			hours -= a.getHours();
			return true;
		}
		return false;
	}
	
	public PowerOutage getFirst() {
		return first;
	}

	public void setFirst(PowerOutage first) {
		this.first = first;
	}

	public Set<PowerOutage> getList() {
		return list;
	}

	public int getPoints() {
		return points;
	}

	public int getHours(int Y) {
		if(isOverY(Y))
			return (hours-((int)lastInsert.getHours()));
		return hours;
	}
	
	/**
	 * Check if the {@link PowerOutage} to add is in the X years compared to the {@link first} PowerOutage of the Solution 
	 * @param a the PowerOutage to be checked
	 * @param X int of years
	 * @return {@code true} if stays in the X years, {@code false} otherwise
	 */
	public boolean isOverX(PowerOutage a, int X) {
		if(first==null)
			return true;
		if(!first.isX(X, a))
			return false;
		return true;
	}
	
	/**
	 * Check if total hours are over the X hours given
	 * @param X total maximum of hours
	 * @return {@code true} if X is overcome, {@code false} otherwise
	 */
	public boolean isOverY(int Y) {
		if(hours>Y)
			return true;
		return false;
	}
	
	public Solution clone() {
		Solution clone = new Solution(this.list, this.first, this.lastInsert, this.points, this.hours);
		return clone;
	}
	
	public String toString() {
		String result = "Number of outages: "+list.size()+"\n"+
				"Customers affected: "+points+"\n"+
				"Total hours of outage: "+hours+"\n";
		for(PowerOutage p : list) {
			result += p.toString()+"\n";
		}
		return result;
	}
}

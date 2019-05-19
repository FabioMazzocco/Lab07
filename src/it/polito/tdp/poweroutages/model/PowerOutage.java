package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class PowerOutage {
	int id;
	int event_type;
	int tag_id;
	int area_id;
	int nerc_id;
	int responsible_id;
	int customers;
	LocalDateTime date_event_began;
	LocalDateTime date_event_finished;
	int demand_loss;
	
	public PowerOutage(int id, int event_type, int tag_id, int area_id, int nerc_id, int responsible_id, int customers,
			LocalDateTime date_event_began, LocalDateTime date_event_finished, int demand_loss) {
		super();
		this.id = id;
		this.event_type = event_type;
		this.tag_id = tag_id;
		this.area_id = area_id;
		this.nerc_id = nerc_id;
		this.responsible_id = responsible_id;
		this.customers = customers;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.demand_loss = demand_loss;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEvent_type() {
		return event_type;
	}
	public void setEvent_type(int event_type) {
		this.event_type = event_type;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public int getNerc_id() {
		return nerc_id;
	}
	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}
	public int getResponsible_id() {
		return responsible_id;
	}
	public void setResponsible_id(int responsible_id) {
		this.responsible_id = responsible_id;
	}
	public int getCustomers() {
		return customers;
	}
	public void setCustomers(int customers) {
		this.customers = customers;
	}
	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}
	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}
	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}
	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}
	public int getDemand_loss() {
		return demand_loss;
	}
	public void setDemand_loss(int demand_loss) {
		this.demand_loss = demand_loss;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		Period period = Period.between(date_event_began.toLocalDate(), date_event_finished.toLocalDate());
		return String.format("%d - %s to %s - %d - %d", date_event_began.getYear(), date_event_began.toString(),
				date_event_finished.toString(), period.getYears(), customers);
	}
	
	public long getHours() {
		Duration duration = Duration.between(date_event_began, date_event_finished);
		long hours = duration.getSeconds()/3600;
		return hours;
	}
	
	/**
	 * Check if the given {@link PowerOutage} is in the time range of X years compared to {@link this} {@link PowerOutage}
	 * @param X the int of years
	 * @param second the other {@link PowerOutage} to check
	 * @return {@code true} if second stays in X years, {@code false} otherwise
	 */
	public boolean isX(int X, PowerOutage second) {
		Period period = Period.between(second.getDate_event_finished().toLocalDate(), this.date_event_began.toLocalDate());
		if(period.getYears() <= X)
			return true;
		return false;
	}
	
		
}

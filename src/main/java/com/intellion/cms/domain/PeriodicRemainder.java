package com.intellion.cms.domain;

public enum PeriodicRemainder {
	Weekly("Weekly"), Biweekly("Biweekly"), 
	Monthly("Monthly"),Bimonthly("Bimonthly"),
	Quarterly("Quarterly"),Halfyearly("Halfyearly"),
	Yearly("Yearly"),None("None");

	private final String periodicRemainder;
	
	private PeriodicRemainder(String periodicRemainder) {
		this.periodicRemainder = periodicRemainder;
	}

	public String getPeriodicRemainder() {
		return periodicRemainder;
	}

	public static PeriodicRemainder getById(String periodicRemainderStr) {
		for(PeriodicRemainder periodicRemainder: PeriodicRemainder.values()) {
			if(periodicRemainder.getPeriodicRemainder().equals(periodicRemainderStr)) {
				return periodicRemainder;
			}
		}
		return null;
	}
}

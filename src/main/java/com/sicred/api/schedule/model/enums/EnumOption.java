package com.sicred.api.schedule.model.enums;

public enum EnumOption {

	YES(1),
	NO(2);

	private int vote;

	EnumOption(int vote) {
		this.vote = vote;
	}

	public int getVote() {
		return vote;
	}

}

package ch.pledarigrond.common.data.common;

import java.io.Serializable;

public enum Language implements Serializable {

	PUTER( "puter"),
	RUMANTSCHGRISCHUN( "rumantschgrischun"),
	SURMIRAN( "surmiran"),
	SURSILVAN( "sursilvan"),
	SUTSILVAN( "sutsilvan"),
	VALLADER( "vallader");

	private final String name;

	public String getName() {
		return name;
	}

	private Language(String name) {
		this.name = name;
	}
}

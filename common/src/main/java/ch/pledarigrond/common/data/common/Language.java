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

	public String getSubtag() {
		switch (name) {
			case "puter":
				return "puter";

			case "rumantschgrischun":
				return "rumgr";

			case "surmiran":
				return "surmiran";

			case "sursilvan":
				return "sursilv";

			case "sutsilvan":
				return "sutsilv";

			case "vallader":
				return "vallader";

			default:
				return "";
		}
	}

	private Language(String name) {
		this.name = name;
	}
}

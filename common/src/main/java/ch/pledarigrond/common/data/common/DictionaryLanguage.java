package ch.pledarigrond.common.data.common;

import java.io.Serializable;

public enum DictionaryLanguage implements Serializable {

	GERMAN( "german"),
	ROMANSH( "romansh");

	private final String name;

	public String getName() {
		return name;
	}

	private DictionaryLanguage(String name) {
		this.name = name;
	}
}

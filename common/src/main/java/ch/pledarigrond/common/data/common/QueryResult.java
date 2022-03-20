package ch.pledarigrond.common.data.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class QueryResult implements Serializable {

	@Serial
	private static final long serialVersionUID = 6228488470783259240L;

	private List<LemmaVersion> entries;
	
	private int totalEntries;

	private int pageSize;
	
	public QueryResult() {
	}

	public QueryResult(List<LemmaVersion> entries, int maxEntries, int pageSize) {
		this.entries = entries;
		this.totalEntries = maxEntries;
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "QueryResult [entries=" + entries + ", totalEntries=" + totalEntries + ", pageSize=" + pageSize + "]";
	}
}

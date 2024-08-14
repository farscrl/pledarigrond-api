package ch.pledarigrond.common.data.lucene;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class to hold statistics data about the lucene index.
 */
@Data
public class IndexStatistics implements Serializable {

	private int numberOfEntries;
	
	private int approvedEntries;
	
	private int unverifiedEntries;
	
	private int unknown;

	private long lastUpdated;

	private HashMap<String, Integer> inflectionCount;

	@Override
	public String toString() {
		return "IndexStatistics [numberOfEntries=" + numberOfEntries
				+ ", approvedEntries=" + approvedEntries
				+ ", unverifiedEntries=" + unverifiedEntries + ", unknown="
				+ unknown + ", lastUpdated=" + lastUpdated + "]";
	}
}

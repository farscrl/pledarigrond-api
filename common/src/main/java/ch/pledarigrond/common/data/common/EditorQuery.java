package ch.pledarigrond.common.data.common;

import lombok.Data;

@Data
public class EditorQuery {
    public static final long ONE_DAY = 1000 * 60 * 60 * 24;

    public static final long HALF_YEAR = ONE_DAY * 31 * 6;

    private LemmaVersion.Status[] state = new LemmaVersion.Status[] {LemmaVersion.Status.NEW_ENTRY, LemmaVersion.Status.NEW_MODIFICATION};

    /**
     * The current text (first name, last name, email, login) used for filtering results
     */
    private String userOrIp;

    /**
     * The current sort column
     */
    private String sortColumn = LemmaVersion.TIMESTAMP;

    /**
     * Whether sort ascending
     */
    private boolean sortAscending = false;

    private long startTime = System.currentTimeMillis() - HALF_YEAR;

    private Role role;

    private long endTime;

    private LemmaVersion.Verification verification;

    private String verifier;
}

package ch.pledarigrond.pronunciation.dto;

import lombok.Data;

@Data
public class RegistrationStatistics {
    private int totalRegistrations;
    private int todoRegistrations;
    private int postponedRegistrationRegistrations;
    private int inReviewRegistrations;
    private int postponedReviewRegistrations;
    private int completedRegistrations;
    private int refusedRegistrations;
}

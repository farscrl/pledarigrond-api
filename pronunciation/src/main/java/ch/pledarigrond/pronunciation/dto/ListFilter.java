package ch.pledarigrond.pronunciation.dto;

import lombok.Data;

@Data
public class ListFilter {
    private String searchTerm;
    private RegistrationStatus status;
    private boolean ascending;
}

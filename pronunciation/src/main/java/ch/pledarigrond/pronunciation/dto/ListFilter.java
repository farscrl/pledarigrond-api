package ch.pledarigrond.pronunciation.dto;

import lombok.Data;

@Data
public class ListFilter {
    private RegistrationStatus status;
    private boolean ascending;
}

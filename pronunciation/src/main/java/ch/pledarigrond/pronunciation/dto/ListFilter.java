package ch.pledarigrond.pronunciation.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListFilter {
    private String searchTerm;
    private RegistrationStatus status;
    private List<RegistrationStatus> statuslist = new ArrayList<>();
    private boolean ascending;
}

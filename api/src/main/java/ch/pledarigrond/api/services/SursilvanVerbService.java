package ch.pledarigrond.api.services;

import ch.pledarigrond.api.dtos.VerbDto;

import java.util.List;

public interface SursilvanVerbService {

    VerbDto getVerb(String verb);

    List<VerbDto> getAllVerbs();
}

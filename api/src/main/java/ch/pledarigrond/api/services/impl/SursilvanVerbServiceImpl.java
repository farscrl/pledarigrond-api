package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.dtos.VerbDto;
import ch.pledarigrond.api.services.SursilvanVerbService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SursilvanVerbServiceImpl implements SursilvanVerbService {
    private HashMap<String, VerbDto> verbsMap = new HashMap<>();

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<VerbDto>> typeReference = new TypeReference<List<VerbDto>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/sursilvan/verbs.json");
        try {
            List<VerbDto> verbs = objectMapper.readValue(inputStream, typeReference);
            for (VerbDto verb : verbs) {
                verbsMap.put(verb.getRmStichwort(), verb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public VerbDto getVerb(String verb) {
        return verbsMap.get(verb);
    }

    public List<VerbDto> getAllVerbs() {
        return new ArrayList<>(verbsMap.values());
    }
}

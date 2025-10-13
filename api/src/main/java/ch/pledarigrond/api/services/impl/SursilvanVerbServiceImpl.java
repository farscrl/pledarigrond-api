package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.dtos.VerbDto;
import ch.pledarigrond.api.services.SursilvanVerbService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SursilvanVerbServiceImpl implements SursilvanVerbService {
    private HashMap<String, VerbDto> verbsMap = new HashMap<>();

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<VerbDto>> typeReference = new TypeReference<List<VerbDto>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/sursilvan/sursilvan_verbs.json");
        try {
            List<VerbDto> verbs = objectMapper.readValue(inputStream, typeReference);
            verbsMap =
                    verbs.stream()
                            .peek(SursilvanVerbServiceImpl::normalizeAllStringFields)
                            .collect(Collectors.toMap(
                                    v -> normalizeText(v.getRmStichwort()),
                                    Function.identity(),
                                    (a, b) -> a,
                                    HashMap::new
                            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public VerbDto getVerb(String verb) {
        return verbsMap.get(normalizeText(verb));
    }

    public List<VerbDto> getAllVerbs() {
        return new ArrayList<>(verbsMap.values());
    }

    static void normalizeAllStringFields(Object bean) {
        Class<?> c = bean.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if (f.getType() == String.class) {
                    f.setAccessible(true);
                    try {
                        String v = (String) f.get(bean);
                        if (v != null) f.set(bean, normalizeText(v));
                    } catch (IllegalAccessException ignore) {}
                }
            }
            c = c.getSuperclass();
        }
    }

    static String normalizeText(String s) {
        if (s == null) return null;
        String nk = Normalizer.normalize(s, Normalizer.Form.NFKC);
        // unify apostrophe-like chars to straight '
        return nk
                .replace('\u2019', '\'') // ’
                .replace('\u2018', '\'') // ‘
                .replace('`',        '\'')
                .replace('\u00B4',   '\''); // ´
    }
}

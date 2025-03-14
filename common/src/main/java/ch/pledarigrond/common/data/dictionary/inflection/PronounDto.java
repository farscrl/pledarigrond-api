package ch.pledarigrond.common.data.dictionary.inflection;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PronounDto {
    @Size(max = 200) private String baseForm;
    @Size(max = 200) private String mSingular;
    @Size(max = 200) private String fSingular;
    @Size(max = 200) private String mPlural;
    @Size(max = 200) private String fPlural;
}

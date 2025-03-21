package ch.pledarigrond.common.data.dictionary.inflection;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AdjectiveDto {
    @Size(max = 200) private String baseForm;
    @Size(max = 200) private String mSingular;
    @Size(max = 200) private String fSingular;
    @Size(max = 200) private String mPlural;
    @Size(max = 200) private String fPlural;
    @Size(max = 200) private String adverbialForm;

    @Size(max = 200) private String predicative;
}

package ch.pledarigrond.common.data.dictionary.inflection;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdjectiveDto {
    @Size(max = 200) private String baseForm;
    private boolean irregular;
    @Size(max = 10) private String inflectionSubtype;

    @Size(max = 200) @JsonProperty("mSingular") private String mSingular;
    @Size(max = 200) @JsonProperty("fSingular") private String fSingular;
    @Size(max = 200) @JsonProperty("mPlural") private String mPlural;
    @Size(max = 200) @JsonProperty("fPlural") private String fPlural;

    @Size(max = 200) private String adverbialForm;
    @Size(max = 200) private String predicative;
}

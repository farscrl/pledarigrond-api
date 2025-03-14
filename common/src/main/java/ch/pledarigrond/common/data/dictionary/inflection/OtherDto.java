package ch.pledarigrond.common.data.dictionary.inflection;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class OtherDto {
    @Size(max = 200) private String baseForm;
    @Size(max = 200) private String otherForm1;
    @Size(max = 200) private String otherForm2;
    @Size(max = 200) private String otherForm3;
    @Size(max = 200) private String otherForm4;
}

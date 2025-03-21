package ch.pledarigrond.common.data.dictionary;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ExampleDto {
    @Size(max = 200) private String rm;
    @Size(max = 200) private String de;
}


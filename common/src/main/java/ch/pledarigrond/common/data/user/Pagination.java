package ch.pledarigrond.common.data.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    @Min(1) @Max(200)
    private int pageSize = 200;
    @Min(0)
    private int page = 0;
}

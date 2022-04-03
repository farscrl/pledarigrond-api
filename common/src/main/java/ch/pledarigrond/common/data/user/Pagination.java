package ch.pledarigrond.common.data.user;

import lombok.Data;

@Data
public class Pagination {
    private int pageSize = 15;
    private int page = 1;
}

package ch.pledarigrond.common.data.user;

import lombok.Data;

@Data
public class Pagination {
    public int pageSize = 15;
    public int page = 1;
}

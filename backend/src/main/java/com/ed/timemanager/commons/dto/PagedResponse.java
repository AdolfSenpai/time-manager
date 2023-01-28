package com.ed.timemanager.commons.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class PagedResponse<T> {
    //region Fields

    private final int pageNumber;

    private final int pageSize;

    private final int pageCount;

    private final List<T> responseList;

    //endregion
}

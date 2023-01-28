package com.ed.timemanager.commons.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class PagedRequest {
    //region Fields

    private final int pageNumber;

    private final int pageSize;

    //endregion    
}

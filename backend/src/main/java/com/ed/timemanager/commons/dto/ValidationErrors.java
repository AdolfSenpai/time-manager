package com.ed.timemanager.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class ValidationErrors {
    //region Fields

    private final Map<String, String> fields;

    private final List<String> global;

    //endregion
}

package com.ed.timemanager.auth_module.controllers.auth.validators.register_request.password;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PasswordRegex {
    //region Enum values

    LETTER_NUMBER(Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")),

    LETTER_NUMBER_SPECIAL(Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")),

    LETTER_UPPER_LOWER_NUMBER(Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")),

    LETTER_UPPER_LOWER_NUMBER_SPECIAL(Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")),

    ;

    //endregion
    //region Fields

    private final Pattern pattern;

    //endregion
}

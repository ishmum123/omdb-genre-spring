package com.newscred.omdb.genre.helpers.dataclass;

import com.newscred.omdb.genre.helpers.validators.annotations.ValidDesignation;
import com.newscred.omdb.genre.helpers.validators.annotations.ValidUserState;
import com.newscred.omdb.genre.helpers.validators.annotations.ValidUserSummary;
import com.newscred.omdb.genre.helpers.validators.annotations.ValidUserType;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ValidUserSummary
public class UserSummary {

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @Size(max = 50)
    private String middlename;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 256, min = 6)
    private String password;

    @Size(max = 15)
    private String phone;

    @Size(max = 100)
    private String address;

    @NotNull
    @ValidUserType
    private int type;

    @NotNull
    @ValidUserState
    private int state;

    @NotNull
    @ValidDesignation
    private int designation;

}

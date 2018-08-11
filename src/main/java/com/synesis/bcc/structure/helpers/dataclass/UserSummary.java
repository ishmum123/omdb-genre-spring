package com.synesis.bcc.structure.helpers.dataclass;

import com.synesis.bcc.structure.helpers.validators.annotations.ValidDesignation;
import com.synesis.bcc.structure.helpers.validators.annotations.ValidUserState;
import com.synesis.bcc.structure.helpers.validators.annotations.ValidUserSummary;
import com.synesis.bcc.structure.helpers.validators.annotations.ValidUserType;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ValidUserSummary
public class UserSummary {

    @NotNull
    @Size(max = 50)
    private String firstname;

    @Size(max = 50)
    private String middlename;

    @NotNull
    @Size(max = 50)
    private String lastname;

    @Email
    @Size(max = 100)
    private String email;

    @NotNull
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

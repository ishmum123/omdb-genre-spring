package com.newscred.omdb.genre.helpers.dataclass;

import lombok.Data;

@Data
public class UserShortSummary {
    private final String name;
    private final String email;
    private final String password;
}

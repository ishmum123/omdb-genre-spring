package com.synesis.bcc.structure.database.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public class MappedBasicDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    @NotNull
    private String name;

}

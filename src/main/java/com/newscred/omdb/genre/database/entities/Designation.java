package com.newscred.omdb.genre.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "designations")
public class Designation extends MappedBasicDetail {

    @JsonIgnore
    @OneToMany(mappedBy = "designation")
    private Set<UserDetail> userDetailSet;

}


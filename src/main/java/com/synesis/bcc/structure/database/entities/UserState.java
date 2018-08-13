package com.synesis.bcc.structure.database.entities;

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
@Table(name = "user_states")
public class UserState extends MappedBasicDetail {

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private Set<UserDetail> userDetailSet;

}

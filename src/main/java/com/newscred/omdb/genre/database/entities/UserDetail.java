package com.newscred.omdb.genre.database.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_details")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private UserType type;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private UserState state;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

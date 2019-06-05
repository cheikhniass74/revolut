package com.money.transfer.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Getter

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"accounts"})
public class User  implements Serializable {


    public static final int EMAILMAX = 255;
    public static final int NAMEMAX = 100;



    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    private List<Account> accounts;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = EMAILMAX)
    @NotNull(message = "email can not be nul")
    private String email;


    @Column(nullable = false, length = NAMEMAX)
    @NotNull(message = "name  can not be nul")
    private String name;


}

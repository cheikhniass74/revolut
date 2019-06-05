package com.money.transfer.entity;

import com.money.transfer.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Builder
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account implements Serializable {
    public static final String SAVING  = "SAVING";
    public  static final String CHECKING  = "CHECKING";

    @Column(nullable = false)
    @NotNull(message = "balance can not be nul")
    private double balance = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;
    @NotNull(message = "accountType can not be nul")
    @Column(nullable = false)
    private String accountType;

    @ManyToOne
    @NotNull(message = "user  not be nul")
    private User user;




}

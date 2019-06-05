package com.money.transfer.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class TransferForm implements Serializable {
    @NotNull(message = "amount can not be nul")
    private double amount;
    @NotNull(message = "From Account can not be nul")
    private long fromAccountNumber;
    @NotNull(message = "To account  can not be nul")
    private long toAccountNumber;
}

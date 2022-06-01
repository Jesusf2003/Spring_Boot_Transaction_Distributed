package com.saga.orderms.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@ToString
public class Payment {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal amount;

    public Payment() {
    }
}

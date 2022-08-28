package com.bsfdc.accounts.entity;

import com.bsfdc.accounts.dto.TransactionHistoryDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "transaction_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "account_id")
    private String accountId;

    @NonNull
    @Column(name = "amount")
    private BigDecimal amount;

    @NonNull
    @Column(name = "transaction_type")
    private Integer transactionType;
}

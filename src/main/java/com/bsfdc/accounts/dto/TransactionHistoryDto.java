package com.bsfdc.accounts.dto;

import com.bsfdc.accounts.entity.Accounts;
import com.bsfdc.accounts.entity.TransactionHistory;
import com.bsfdc.accounts.enums.TransactionTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionHistoryDto implements Serializable {

    @JsonIgnore
    private Long id;
    private String accountId;
    private BigDecimal amount;
    private TransactionTypes transactionType;



}

package com.bsfdc.accounts.dto;

import com.bsfdc.accounts.entity.Accounts;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountsDto implements Serializable {

    @JsonIgnore
    private Long id;
    private String accountId;
    private Long userId;
    private BigDecimal balance;

    public Accounts toAccountsEntity(){
        return Accounts.builder().id(this.id).balance(this.getBalance()).userId(this.getUserId()).accountId(this.accountId).build();
    }
}

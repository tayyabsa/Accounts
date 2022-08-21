package com.bsfdc.accounts.entity;

import com.bsfdc.accounts.dto.AccountsDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Accounts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "user_id")
    private Long userId;

    @NonNull
    @Column(name = "account_id")
    private String accountId;

    @NonNull
    @Column(name = "balance")
    private BigDecimal balance;

    public AccountsDto toAccountsDto(){
        return AccountsDto.builder().id(this.id).balance(this.getBalance()).userId(this.getUserId()).accountId(this.accountId).build();
    }
}

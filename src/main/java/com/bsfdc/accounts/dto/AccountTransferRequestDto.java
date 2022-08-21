package com.bsfdc.accounts.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountTransferRequestDto implements Serializable {


    @NotNull(message = "Source account id can not be null")
    private String sourceAccountId;

    @NotNull(message = "Destination account id can not be null")
    private String destinationAccountId;

    @NotNull(message = "Amount can not be null")
    private BigDecimal amount;
}

package com.bankaccount.account.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;


public class AccountCreditedEvent extends AbstractAccountEvent {

    @JsonProperty("amount")
    private final double amount;

    @JsonCreator
    public AccountCreditedEvent(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("timestamp") Date timestamp,
            @JsonProperty("amount") double amount
    ) {
        super(accountId, timestamp);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

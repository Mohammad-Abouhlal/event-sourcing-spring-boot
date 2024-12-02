package com.bankaccount.account.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;


public class MoneyWithdrawnEvent extends AbstractAccountEvent {

    @JsonProperty("amount")
    private final double amount;

    @JsonCreator
    public MoneyWithdrawnEvent(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("amount") double amount,
            @JsonProperty("timestamp") Date timestamp
    ) {
        super(accountId, timestamp);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

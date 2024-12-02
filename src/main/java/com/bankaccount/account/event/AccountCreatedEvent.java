package com.bankaccount.account.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class AccountCreatedEvent extends AbstractAccountEvent {

    @JsonProperty("owner")
    private final String owner;

    @JsonProperty("initialBalance")
    private final double initialBalance;

    @JsonCreator
    public AccountCreatedEvent(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("owner") String owner,
            @JsonProperty("initialBalance") double initialBalance,
            @JsonProperty("timestamp") Date timestamp
    ) {
        super(accountId, timestamp);
        this.owner = owner;
        this.initialBalance = initialBalance;
    }

    public String getOwner() {
        return owner;
    }

    public double getInitialBalance() {
        return initialBalance;
    }
}

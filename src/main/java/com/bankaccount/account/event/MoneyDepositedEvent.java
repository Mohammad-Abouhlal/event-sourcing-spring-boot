package com.bankaccount.account.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

/*
* A deposit operation is the process of adding funds to an account. In the context of banking and financial systems,
* it represents an action where a certain amount of money is transferred into an account, increasing the account balance.
* */

public class MoneyDepositedEvent extends AbstractAccountEvent {

    @JsonProperty("amount")
    private final double amount;

    @JsonCreator
    public MoneyDepositedEvent(
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

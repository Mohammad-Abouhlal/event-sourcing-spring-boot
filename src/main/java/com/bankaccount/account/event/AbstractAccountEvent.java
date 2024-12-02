package com.bankaccount.account.event;

import java.util.Date;


public abstract class AbstractAccountEvent {

    private final String accountId;

    private final Date timestamp;


    public AbstractAccountEvent(String accountId, Date timestamp) {
        this.accountId = accountId;
        this.timestamp = timestamp;
    }

    public String getAccountId() {
        return accountId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
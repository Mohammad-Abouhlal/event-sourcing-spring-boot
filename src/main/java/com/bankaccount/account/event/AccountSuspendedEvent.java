package com.bankaccount.account.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;


public class AccountSuspendedEvent extends AbstractAccountEvent {

    @JsonProperty("reason")
    private final String reason;

    @JsonCreator
    public AccountSuspendedEvent(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("timestamp") Date timestamp,
            @JsonProperty("reason") String reason
    ) {
        super(accountId, timestamp);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}

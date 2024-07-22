package eminozkan.transaction.service.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateTransactionRequest {
    LocalDate date;

    BigDecimal amount;

    public LocalDate getDate() {
        return date;
    }

    public UpdateTransactionRequest setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UpdateTransactionRequest setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}

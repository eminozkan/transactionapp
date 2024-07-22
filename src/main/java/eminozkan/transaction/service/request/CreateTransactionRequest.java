package eminozkan.transaction.service.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTransactionRequest {
    BigDecimal amount;

    LocalDate date;

    public BigDecimal getAmount() {
        return amount;
    }

    public CreateTransactionRequest setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public CreateTransactionRequest setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}

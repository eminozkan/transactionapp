package eminozkan.transaction.service.transaction;

import eminozkan.transaction.model.Transaction;
import eminozkan.transaction.service.request.CreateTransactionRequest;
import eminozkan.transaction.service.request.UpdateTransactionRequest;
import eminozkan.transaction.util.result.CrudResult;

import java.util.List;

public interface TransactionService {
    CrudResult save(String userId, CreateTransactionRequest request);

    CrudResult update(String transactionId, UpdateTransactionRequest request);

    void delete(String transactionId);

    List<Transaction> getTransactions(String userId);
}

package eminozkan.transaction.service.transaction;

import eminozkan.transaction.model.Transaction;
import eminozkan.transaction.model.User;
import eminozkan.transaction.repository.TransactionRepository;
import eminozkan.transaction.service.request.CreateTransactionRequest;
import eminozkan.transaction.service.request.UpdateTransactionRequest;
import eminozkan.transaction.util.result.CrudResult;
import eminozkan.transaction.util.result.OperationFailureReason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository repository;

    private final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }


    @Override
    public CrudResult save(String userId, CreateTransactionRequest request) {
        var transaction = new Transaction()
                .setDate(request.getDate())
                .setAmount(request.getAmount());

        repository.save(transaction);
        log.info("Transaction has been saved");
        return CrudResult.success("Transaction has been saved");
    }

    @Override
    public CrudResult update(String transactionId, UpdateTransactionRequest request) {
        var transactionOptional = repository.findById(transactionId);
        if (transactionOptional.isEmpty()){
            return CrudResult.failed(OperationFailureReason.NOT_FOUND,"Transaction has been not found");
        }

        var transaction = transactionOptional.get();
        if (request.getDate() != null){
            transaction.setDate(request.getDate());
        }
        if (request.getAmount() != null){
            transaction.setAmount(request.getAmount());
        }
        repository.save(transaction);
        return CrudResult.success("Transaction has been updated");
    }

    @Override
    public void delete(String transactionId) {
        repository.deleteById(transactionId);
    }

    @Override
    public List<Transaction> getTransactions(String userId) {
        var user = new User()
                .setUserId(userId);

        return repository.findByUser(user);
    }
}

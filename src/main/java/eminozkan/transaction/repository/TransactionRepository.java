package eminozkan.transaction.repository;

import eminozkan.transaction.model.Transaction;
import eminozkan.transaction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByUser(User user);
}

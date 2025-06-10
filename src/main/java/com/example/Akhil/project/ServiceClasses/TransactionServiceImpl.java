package com.stport.spm.ServiceClasses;

import com.stport.spm.DTOClasses.TransactionDTO;
import com.stport.spm.Tables.TransactionRepo;
import com.stport.spm.Tables.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepo transactionRepo;

    @Override
    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
        if (transactionDTO == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        if (transactionDTO.getT_id() != null) {
            transaction.setT_id(transactionDTO.getT_id());
        }
        return getTransactionDTO(transactionDTO, transaction);
    }

    private TransactionDTO getTransactionDTO(TransactionDTO transactionDTO, Transaction transaction) {
        if (transactionDTO == null || transaction == null) {
            return null;
        }
        transaction.setPrice(transactionDTO.getPrice());
        transaction.setQty(transactionDTO.getQty());
        transaction.setStock_sym(transactionDTO.getStock_sym());
        transaction.setTimestamp_t(transactionDTO.getTimestamp_t());
        transaction.setType(transactionDTO.getType());
        transaction.setUsers(transactionDTO.getUsers());
        Transaction savedTransaction = transactionRepo.save(transaction);
        return getTransactionDTO(savedTransaction);
    }

    private TransactionDTO getTransactionDTO(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setT_id(transaction.getT_id());
        transactionDTO.setPrice(transaction.getPrice());
        transactionDTO.setQty(transaction.getQty());
        transactionDTO.setStock_sym(transaction.getStock_sym());
        transactionDTO.setTimestamp_t(transaction.getTimestamp_t());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setUsers(transaction.getUsers());
        return transactionDTO;
    }

    @Override
    public TransactionDTO getTransaction(Integer t_id) {
        if (t_id == null) {
            return null;
        }
        Transaction transaction = transactionRepo.findById(t_id).orElse(null);
        return getTransactionDTO(transaction);
    }

    @Override
    public TransactionDTO updateTransaction(Integer t_id, TransactionDTO transactionDTO) {
        if (t_id == null || transactionDTO == null) {
            return null;
        }
        Transaction transaction = transactionRepo.findById(t_id).orElse(null);
        if (transaction == null) {
            return null;
        }
        return getTransactionDTO(transactionDTO, transaction);
    }

    @Override
    public String deleteTransaction(Integer t_id) {
        if (t_id == null) {
            return "Invalid transaction ID";
        }
        Transaction transaction = transactionRepo.findById(t_id).orElse(null);
        if (transaction == null) {
            return "Transaction not found";
        }
        transactionRepo.delete(transaction);
        return "Transaction deleted";
    }
}

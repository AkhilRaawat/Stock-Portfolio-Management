package com.stport.spm.Controllers;

import com.stport.spm.DTOClasses.TransactionDTO;
import com.stport.spm.ServiceClasses.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transaction")
@AllArgsConstructor
public class TransactionController {
    private TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            if (transactionDTO.getT_id() == null || transactionDTO.getT_id().toString().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TransactionDTO savedTransaction = transactionService.addTransaction(transactionDTO);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @GetMapping("{t_id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Integer t_id) {
        TransactionDTO transactionDTO = transactionService.getTransaction(t_id);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @PutMapping("{t_id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Integer t_id, @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO updatedTransaction = transactionService.updateTransaction(t_id, transactionDTO);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    @DeleteMapping("{t_id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Integer t_id) {
        String msg = transactionService.deleteTransaction(t_id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}

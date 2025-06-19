package com.example.Akhil.project.Controllers;

import com.example.Akhil.project.DTOClasses.StocksDTO;
import com.example.Akhil.project.ServiceClasses.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stocks")
@AllArgsConstructor
public class StockController {
    private StockService stockService;
    @PostMapping()
    public ResponseEntity<StocksDTO> addStocks(@RequestBody StocksDTO stocksDTO) {
        try {
            if (stocksDTO.getName() == null || stocksDTO.getName().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StocksDTO savedStock = stockService.addStocks(stocksDTO);
        return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
    }

    @GetMapping("{stock_id}")
    public ResponseEntity<StocksDTO> getStocks(@PathVariable Integer stock_id) {
        StocksDTO stocksDTO = stockService.getStocks(stock_id);
        return new ResponseEntity<>(stocksDTO, HttpStatus.OK);
    }

    @PutMapping("{stock_id}")
    public ResponseEntity<StocksDTO> updateStocks(@PathVariable Integer stock_id, @RequestBody StocksDTO stocksDTO) {
        StocksDTO updatedStock = stockService.updateStocks(stock_id, stocksDTO);
        return new ResponseEntity<>(updatedStock, HttpStatus.OK);
    }

    @DeleteMapping("{stock_id}")
    public ResponseEntity<String> deleteStocks(@PathVariable Integer stock_id) {
        String msg = stockService.deleteStocks(stock_id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}

package com.stport.spm.ServiceClasses;

import com.stport.spm.DTOClasses.StocksDTO;
import com.stport.spm.Tables.Stocks;
import com.stport.spm.Tables.StocksRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {

    private StocksRepo stocksRepo;

    @Override
    public StocksDTO addStocks(StocksDTO stocksDTO) {
        Stocks stocks = new Stocks();
        if (stocksDTO.getStock_id() != null) {
            stocks.setStock_id(stocksDTO.getStock_id());
        }
        return getStocksDTO(stocksDTO, stocks);
    }

    private StocksDTO getStocksDTO(Stocks stocks) {
        if (stocks == null) {
            return null;
        }
        StocksDTO stocksDTO = new StocksDTO();
        stocksDTO.setStock_id(stocks.getStock_id());
        stocksDTO.setName(stocks.getName());
        stocksDTO.setStock_sym(stocks.getStock_sym());
        stocksDTO.setDay_before_price(stocks.getDay_before_price());
        stocksDTO.setMarket_cap(stocks.getMarket_cap());
        stocksDTO.setCurrent_price(stocks.getCurrent_price());
        stocksDTO.setPortfolios(stocks.getPortfolios());
        return stocksDTO;
    }

    @Override
    public StocksDTO getStocks(Integer stock_id) {
        if (stock_id == null) {
            return null;
        }
        Stocks stocks = stocksRepo.findById(stock_id).orElse(null);
        return getStocksDTO(stocks);
    }

    @Override
    public StocksDTO updateStocks(Integer stock_id, StocksDTO stocksDTO) {
        if (stock_id == null || stocksDTO == null) {
            return null;
        }
        Stocks stocks = stocksRepo.findById(stock_id).orElse(null);
        if (stocks == null) {
            return null;
        }
        return getStocksDTO(stocksDTO, stocks);
    }

    private StocksDTO getStocksDTO(StocksDTO stocksDTO, Stocks stocks) {
        if (stocksDTO == null || stocks == null) {
            return null;
        }
        stocks.setName(stocksDTO.getName());
        stocks.setStock_sym(stocksDTO.getStock_sym());
        stocks.setDay_before_price(stocksDTO.getDay_before_price());
        stocks.setMarket_cap(stocksDTO.getMarket_cap());
        stocks.setCurrent_price(stocksDTO.getCurrent_price());
        stocks.setPortfolios(stocksDTO.getPortfolios());
        Stocks savedStock = stocksRepo.save(stocks);
        return getStocksDTO(savedStock);
    }

    @Override
    public String deleteStocks(Integer stock_id) {
        if (stock_id == null) {
            return "Invalid stock ID";
        }
        Stocks stocks = stocksRepo.findById(stock_id).orElse(null);
        if (stocks == null) {
            return "Stock not found";
        }
        String msg = getStocksDTO(stocks).toString();
        stocksRepo.delete(stocks);
        return msg;
    }

}

package com.example.Akhil.project.ServiceClasses;


import com.example.Akhil.project.DTOClasses.StocksDTO;

public interface StockService {
    StocksDTO addStocks(StocksDTO stocksDTO);
    StocksDTO getStocks(Integer stock_id);
    StocksDTO updateStocks(Integer stock_id, StocksDTO stocksDTO);
    String deleteStocks(Integer stock_id);
}

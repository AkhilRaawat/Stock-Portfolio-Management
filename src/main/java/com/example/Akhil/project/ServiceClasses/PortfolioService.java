package com.example.Akhil.project.ServiceClasses;

import com.example.Akhil.project.DTOClasses.PortfolioDTO;

public interface PortfolioService {
    PortfolioDTO addPortfolio(PortfolioDTO portfolioDTO);
    PortfolioDTO getPortfolio(Integer portfolio_id);
    PortfolioDTO updatePortfolio(Integer portfolio_id, PortfolioDTO portfolioDTO);
    String deletePortfolio(Integer portfolio_id);
}

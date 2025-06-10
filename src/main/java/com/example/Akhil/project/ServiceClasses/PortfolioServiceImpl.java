package com.stport.spm.ServiceClasses;

import com.stport.spm.DTOClasses.PortfolioDTO;
import com.stport.spm.Tables.Portfolio;
import com.stport.spm.Tables.PortfolioRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRepo portfolioRepo;
    
    @Override
    public PortfolioDTO addPortfolio(PortfolioDTO portfolioDTO) {
        if (portfolioDTO == null) {
            return null;
        }
        Portfolio portfolio = new Portfolio();
        if (portfolioDTO.getPortfolio_id() != null) {
            portfolio.setPortfolio_id(portfolioDTO.getPortfolio_id());
        }
        return getPortfolioDTO(portfolioDTO, portfolio);
    }

    private PortfolioDTO getPortfolioDTO(Portfolio portfolio) {
        if (portfolio == null) {
            return null;
        }
        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setPortfolio_id(portfolio.getPortfolio_id());
        portfolioDTO.setShares(portfolio.getShares());
        portfolioDTO.setStock_id(portfolio.getStock_id());
        portfolioDTO.setCurrent_price(portfolio.getCurrent_price());
        portfolioDTO.setCost_basis(portfolio.getCost_basis());
        portfolioDTO.setYield(portfolio.getYield());
        portfolioDTO.setUser(portfolio.getUser());
        portfolioDTO.setStocks(portfolio.getStocks());
        return portfolioDTO;
    }

    private PortfolioDTO getPortfolioDTO(PortfolioDTO portfolioDTO, Portfolio portfolio) {
        if (portfolioDTO == null || portfolio == null) {
            return null;
        }
        portfolio.setShares(portfolioDTO.getShares());
        portfolio.setStock_id(portfolioDTO.getStock_id());
        portfolio.setCurrent_price(portfolioDTO.getCurrent_price());
        portfolio.setCost_basis(portfolioDTO.getCost_basis());
        portfolio.setYield(portfolioDTO.getYield());
        portfolio.setUser(portfolioDTO.getUser());
        portfolio.setStocks(portfolioDTO.getStocks());
        Portfolio savedPortfolio = portfolioRepo.save(portfolio);
        return getPortfolioDTO(savedPortfolio);
    }

    @Override
    public PortfolioDTO getPortfolio(Integer portfolio_id) {
        if (portfolio_id == null) {
            return null;
        }
        Portfolio portfolio = portfolioRepo.findById(portfolio_id).orElse(null);
        return getPortfolioDTO(portfolio);
    }

    @Override
    public PortfolioDTO updatePortfolio(Integer portfolio_id, PortfolioDTO portfolioDTO) {
        if (portfolio_id == null || portfolioDTO == null) {
            return null;
        }
        Portfolio portfolio = portfolioRepo.findById(portfolio_id).orElse(null);
        if (portfolio == null) {
            return null;
        }
        return getPortfolioDTO(portfolioDTO, portfolio);
    }

    @Override
    public String deletePortfolio(Integer portfolio_id) {
        if (portfolio_id == null) {
            return "Invalid portfolio ID";
        }
        Portfolio portfolio = portfolioRepo.findById(portfolio_id).orElse(null);
        if (portfolio == null) {
            return "Portfolio not found";
        }
        portfolioRepo.delete(portfolio);
        return "Portfolio deleted";
    }
}

package com.example.Akhil.project.Controllers;

import com.example.Akhil.project.DTOClasses.PortfolioDTO;
import com.example.Akhil.project.ServiceClasses.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/portfolio")
@AllArgsConstructor
public class PortfolioController {
    private PortfolioService portfolioService;

    @PostMapping()
    public ResponseEntity<PortfolioDTO> addPortfolio(@RequestBody PortfolioDTO portfolioDTO) {
        try {
            if (portfolioDTO.getPortfolio_id() == null || portfolioDTO.getPortfolio_id().toString().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PortfolioDTO savedPortfolio = portfolioService.addPortfolio(portfolioDTO);
        return new ResponseEntity<>(savedPortfolio, HttpStatus.CREATED);
    }

    @GetMapping("{portfolio_id}")
    public ResponseEntity<PortfolioDTO> getPortfolio(@PathVariable Integer portfolio_id) {
        PortfolioDTO portfolioDTO = portfolioService.getPortfolio(portfolio_id);
        return new ResponseEntity<>(portfolioDTO, HttpStatus.OK);
    }

    @PutMapping("{portfolio_id}")
    public ResponseEntity<PortfolioDTO> updatePortfolio(@PathVariable Integer portfolio_id, @RequestBody PortfolioDTO portfolioDTO) {
        PortfolioDTO updatedPortfolio = portfolioService.updatePortfolio(portfolio_id, portfolioDTO);
        return new ResponseEntity<>(updatedPortfolio, HttpStatus.OK);
    }

    @DeleteMapping("{portfolio_id}")
    public ResponseEntity<String> deletePortfolio(@PathVariable Integer portfolio_id) {
        String msg = portfolioService.deletePortfolio(portfolio_id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}

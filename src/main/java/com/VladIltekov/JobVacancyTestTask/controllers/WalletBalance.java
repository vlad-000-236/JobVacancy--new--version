package com.VladIltekov.JobVacancyTestTask.controllers;

import com.VladIltekov.JobVacancyTestTask.models.Wallet;
import com.VladIltekov.JobVacancyTestTask.services.WalletService;
import com.VladIltekov.JobVacancyTestTask.util.WalletErrorResponse;
import com.VladIltekov.JobVacancyTestTask.util.WalletNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WalletBalance {

    private final WalletService walletService;
    private Wallet wallet;

    @Autowired
    public WalletBalance(WalletService walletService, Wallet wallet) {
        this.walletService = walletService;
        this.wallet = wallet;
    }

    @GetMapping("wallets/{WALLET_UUID}")
    public String walletBalance(@PathVariable("WALLET_UUID") String uuid){
        wallet = walletService.findWalletBalanceDto(uuid);
        String response = String.valueOf(wallet.getWalletBalance());
        return response;
    }

    @ExceptionHandler
    private ResponseEntity <WalletErrorResponse> handleException(WalletNotFoundException e){
        WalletErrorResponse errorResponse = new WalletErrorResponse("Wallet with this UUID wasn't found!");

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

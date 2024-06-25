package com.VladIltekov.JobVacancyTestTask.services;

import org.springframework.stereotype.Component;

@Component
public class WalletDepositAction {
    private double response = 0;
    public double walletDepositAction(double walletBalance, double amount){
        response = walletBalance + amount;
        return response;
    }
}

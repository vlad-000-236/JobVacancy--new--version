package com.VladIltekov.JobVacancyTestTask.services;

import org.springframework.stereotype.Component;

@Component
public class WalletWithdrawAction {
    private double response;

    public double walletWithdrawMethod(double walletBalance, double amount){
        if(walletBalance == 0.00 || amount > walletBalance){
            response = -1.00;
        }
        else{
            response = walletBalance - amount;
        }
        return response;
    }
}

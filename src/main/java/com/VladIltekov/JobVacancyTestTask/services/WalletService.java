package com.VladIltekov.JobVacancyTestTask.services;

import com.VladIltekov.JobVacancyTestTask.models.PostResponseModel;
import com.VladIltekov.JobVacancyTestTask.models.Wallet;
import org.springframework.stereotype.Component;

@Component

public interface WalletService {
    Wallet findWalletBalanceDto(String walletName);
    PostResponseModel walletDeposit(String walletName, double amount, String userOperationType);
    PostResponseModel walletWithdraw(String walletName, double amount, String userOperationType);

    PostResponseModel failedWalletOperation(String walletName, double amount, String userOperationType);
}

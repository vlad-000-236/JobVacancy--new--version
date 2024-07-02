package com.VladIltekov.JobVacancyTestTask.services;

import com.VladIltekov.JobVacancyTestTask.models.PostResponseModel;
import com.VladIltekov.JobVacancyTestTask.models.Wallet;
import com.VladIltekov.JobVacancyTestTask.repositories.WalletRepository;
import com.VladIltekov.JobVacancyTestTask.util.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImplementation implements WalletService {

    private final WalletRepository walletRepository;

    private final WalletDepositAction walletDepositAction;
    private final WalletWithdrawAction walletWithdrawAction;
    private Wallet wallet;

    @Autowired
    public WalletServiceImplementation(WalletDepositAction walletDepositAction,
                                       WalletWithdrawAction walletWithdrawAction,
                                       Wallet wallet, WalletRepository walletRepository){
        this.walletDepositAction = walletDepositAction;
        this.walletWithdrawAction = walletWithdrawAction;
        this.wallet = wallet;
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Wallet findWalletBalanceDto(String walletName){
        Optional<Wallet> foundWallet = walletRepository.findById(walletName);

        return  foundWallet.orElseThrow(WalletNotFoundException::new);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PostResponseModel walletDeposit(String walletName, double amount, String userOperationType){
        PostResponseModel postResponseModel = new PostResponseModel();
        wallet = findWalletBalanceDto(walletName);
        double walletBalance = wallet.getWalletBalance();

        postResponseModel.setPreviousWalletBalance(String.valueOf(walletBalance));

        if(!wallet.getWalletName().equals(null)){
            wallet.setWalletBalance(walletDepositAction.walletDepositAction(walletBalance, amount));
            walletRepository.save(wallet);

            postResponseModel.setMessage("Operation successful!");
            postResponseModel.setWalletName(walletName);
            postResponseModel.setNewWalletBalance(String.valueOf(wallet.getWalletBalance()));
            postResponseModel.setAmount(String.valueOf(amount));
            postResponseModel.setUsersOperationType(userOperationType);
        }
        else {
            postResponseModel.setMessage("Operation failed!");
            postResponseModel.setWalletName(walletName);
            postResponseModel.setNewWalletBalance(String.valueOf(wallet.getWalletBalance()));
            postResponseModel.setAmount(String.valueOf(amount));
            postResponseModel.setUsersOperationType(userOperationType);
        }
        return postResponseModel;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PostResponseModel walletWithdraw(String walletName, double amount, String userOperationType) {
        PostResponseModel postResponseModel = new PostResponseModel();
        wallet = findWalletBalanceDto(walletName);
        double walletBalance = wallet.getWalletBalance();

        postResponseModel.setPreviousWalletBalance(String.valueOf(walletBalance));

        if (walletWithdrawAction.walletWithdrawMethod(walletBalance, amount) != -1) {
            wallet.setWalletBalance(walletWithdrawAction.walletWithdrawMethod(walletBalance, amount));
            walletRepository.save(wallet);

            postResponseModel.setWalletName(walletName);
            postResponseModel.setMessage("Operation successful!");
            postResponseModel.setNewWalletBalance(String.valueOf(wallet.getWalletBalance()));
            postResponseModel.setAmount(String.valueOf(amount));
            postResponseModel.setUsersOperationType(userOperationType);
        }
        else{
            postResponseModel.setWalletName(walletName);
            postResponseModel.setMessage("Operation failed!");
            postResponseModel.setNewWalletBalance(String.valueOf(wallet.getWalletBalance()));
            postResponseModel.setAmount(String.valueOf(amount));
            postResponseModel.setUsersOperationType(userOperationType);
        }

        return postResponseModel;
    }

    @Override
    public PostResponseModel failedWalletOperation(String walletName, double amount, String userOperationType) {
        PostResponseModel postResponseModel = new PostResponseModel();
        wallet = findWalletBalanceDto(walletName);

        postResponseModel.setMessage("Wallet operation is failed!");
        postResponseModel.setWalletName(walletName);
        postResponseModel.setUsersOperationType(userOperationType);
        postResponseModel.setPreviousWalletBalance(String.valueOf(wallet.getWalletBalance()));
        postResponseModel.setNewWalletBalance(String.valueOf(wallet.getWalletBalance()));
        postResponseModel.setAmount(String.valueOf(amount));

        return postResponseModel;
    }
}

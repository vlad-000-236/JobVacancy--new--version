package com.VladIltekov.JobVacancyTestTask.controllers;


import com.VladIltekov.JobVacancyTestTask.models.PostParamModel;
import com.VladIltekov.JobVacancyTestTask.models.PostResponseModel;
import com.VladIltekov.JobVacancyTestTask.models.Wallet;
import com.VladIltekov.JobVacancyTestTask.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletOperation {

    private final WalletService walletService;
    private Wallet wallet;

    @Autowired
    public WalletOperation(WalletService walletService, Wallet wallet){
        this.walletService = walletService;
        this.wallet = wallet;
    }

    @PostMapping
    public PostResponseModel walletOperation(@RequestBody PostParamModel postParamModel) {

            PostResponseModel postResponseModel = new PostResponseModel();

            switch(postParamModel.getOperationType()){

                case "DEPOSIT":
                    postResponseModel = walletService.walletDeposit(postParamModel.getValletId(), postParamModel.getAmount(),
                            postParamModel.getOperationType());
                    break;

                case "WITHDRAW":
                    postResponseModel = walletService.walletWithdraw(postParamModel.getValletId(), postParamModel.getAmount(),
                            postParamModel.getOperationType());
                    break;

                default:
                    postResponseModel = walletService.failedWalletOperation(postParamModel.getValletId(), postParamModel.getAmount(),
                            postParamModel.getOperationType());
                    break;
            }
        return postResponseModel;
    }
}

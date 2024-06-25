package com.VladIltekov.JobVacancyTestTask.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PostResponseModel {

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private String walletName;

    @Setter
    @Getter
    private String usersOperationType;

    @Setter
    @Getter
    private String previousWalletBalance;

    @Setter
    @Getter
    private String amount;


    @Setter
    @Getter
    private String newWalletBalance;
}

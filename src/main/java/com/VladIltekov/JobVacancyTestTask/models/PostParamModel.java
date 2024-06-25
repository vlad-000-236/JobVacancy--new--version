package com.VladIltekov.JobVacancyTestTask.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class PostParamModel {

    @Setter
    @Getter
    private String valletId;

    @Getter
    @Setter
    private String operationType;

    @Getter
    @Setter
    double amount;
}

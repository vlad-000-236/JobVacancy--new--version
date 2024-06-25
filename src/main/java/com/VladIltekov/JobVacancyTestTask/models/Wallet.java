package com.VladIltekov.JobVacancyTestTask.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "wallets")
@Entity
@Component
public class Wallet {

    @Column(name = "id")
    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "walletname")
    private String walletName;

    @Getter
    @Setter
    @Column(name = "walletbalance")
    private double walletBalance;
}

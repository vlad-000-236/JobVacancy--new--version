package com.VladIltekov.JobVacancyTestTask.repositories;

import com.VladIltekov.JobVacancyTestTask.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
}

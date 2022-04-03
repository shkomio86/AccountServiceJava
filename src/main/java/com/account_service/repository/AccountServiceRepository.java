package com.account_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.account_service.models.AccountServiceModel;

public interface AccountServiceRepository extends JpaRepository<AccountServiceModel, Integer> {
	@Query("SELECT new AccountServiceModel(u.id, u.name, u.email, u.password, u.locked) FROM AccountServiceModel u WHERE u.email = ?1")
	AccountServiceModel findByEmail(String email);
}

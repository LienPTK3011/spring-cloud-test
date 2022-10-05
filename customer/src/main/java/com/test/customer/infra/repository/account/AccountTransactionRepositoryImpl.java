package com.test.customer.infra.repository.account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.customer.domain.AccountTransaction;
import com.test.customer.infra.entity.AccountTransactionEntity;
import com.test.customer.infra.entity.BankAccountEntity;
import com.test.customer.infra.jpa.JpaAccountTransactionRepository;

@Component
public class AccountTransactionRepositoryImpl implements AccountTransactionRepository {

	@Autowired
	private JpaAccountTransactionRepository accountTransactionRepository;

	@Override
	public List<AccountTransaction> findTransactionnByPeirod(LocalDateTime startDate, LocalDateTime endDate) {
		return this.accountTransactionRepository.findByDatePeriod(startDate, endDate)
				.stream()
				.map(t -> this.toDomain(t))
				.collect(Collectors.toList());
	}

	@Override
	public void addTransaction(AccountTransaction accountTransaction) {
		this.accountTransactionRepository.save(this.toEntity(accountTransaction));
	}
	
	private AccountTransaction toDomain(AccountTransactionEntity entity) {
		return AccountTransaction.builder()
					.amount(entity.getAmount())
					.transactionDate(entity.getTransactionDate())
					.build();
	}

	private AccountTransactionEntity toEntity(AccountTransaction domain) {
		return AccountTransactionEntity.builder()
					.amount(domain.getAmount())
					.bankAccount(BankAccountEntity.builder().id(domain.getBankAccount().getId()).build())
					.transactionDate(domain.getTransactionDate())
					.build();
	}

}

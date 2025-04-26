package com.Test.Test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Test.Test.models.PaymentType;

public interface PaymentRepository extends JpaRepository<PaymentType, Integer> {
}

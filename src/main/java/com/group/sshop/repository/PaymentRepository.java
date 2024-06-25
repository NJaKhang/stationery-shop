package com.group.sshop.repository;

import com.group.sshop.models.entities.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {

}

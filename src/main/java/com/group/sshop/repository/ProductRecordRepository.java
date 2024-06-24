package com.group.sshop.repository;

import com.group.sshop.models.entities.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRecordRepository extends JpaRepository<ProductRecord, Long> {
}

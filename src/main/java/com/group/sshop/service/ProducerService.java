package com.group.sshop.service;

import com.group.sshop.models.dto.ProductForm;
import com.group.sshop.models.entities.Producer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProducerService {

    List<Producer> findAll();

}

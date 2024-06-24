package com.group.sshop.service.impl;

import com.group.sshop.models.dto.ProductForm;
import com.group.sshop.models.entities.Producer;
import com.group.sshop.repository.ProducerRepository;
import com.group.sshop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

}

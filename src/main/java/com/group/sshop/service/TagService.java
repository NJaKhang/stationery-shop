package com.group.sshop.service;

import com.group.sshop.models.dto.TagForm;
import com.group.sshop.models.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    void create(TagForm tagForm);
}

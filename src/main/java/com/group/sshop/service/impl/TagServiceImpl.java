package com.group.sshop.service.impl;

import com.group.sshop.models.dto.TagForm;
import com.group.sshop.models.entities.Tag;
import com.group.sshop.repository.TagRepository;
import com.group.sshop.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public void create(TagForm tagForm) {
        Tag tag = new Tag();
        tag.setName(tagForm.getName());
        tag.setAlias(tagForm.getAlias());
        tagRepository.save(tag);
    }
}

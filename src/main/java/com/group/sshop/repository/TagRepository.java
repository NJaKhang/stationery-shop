package com.group.sshop.repository;

import com.group.sshop.models.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}

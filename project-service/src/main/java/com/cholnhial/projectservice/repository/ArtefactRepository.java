package com.cholnhial.projectservice.repository;

import com.cholnhial.projectservice.model.Artefact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtefactRepository extends JpaRepository<Artefact, Long> {
}

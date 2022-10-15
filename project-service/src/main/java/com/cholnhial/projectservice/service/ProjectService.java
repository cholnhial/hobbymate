package com.cholnhial.projectservice.service;

import com.cholnhial.projectservice.model.Artefact;
import com.cholnhial.projectservice.model.Project;
import com.cholnhial.projectservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private void createArtefactForProject(Project project) {
        Artefact projectArtefact = new Artefact();
        projectArtefact.setPrice(new BigDecimal("0.00"));
        projectArtefact.setName(project.getTitle());
        projectArtefact.setDescription("-- DESCRIPTION HERE --");
        projectArtefact.setPicture("https://picsum.photos/200");
        project.setArtefact(projectArtefact);
    }

    @Transactional
    public Project saveNewProject(Project project) {
        createArtefactForProject(project);
        return this.projectRepository.save(project);
    }

    @Transactional
    public Project updateProject(Project project) {
        return this.projectRepository.save(project);
    }

    public List<Project> getAll() {
        return this.projectRepository.findAll();
    }

    public Optional<Project> getOneById(Long id) {
        return this.projectRepository.findById(id);
    }
}

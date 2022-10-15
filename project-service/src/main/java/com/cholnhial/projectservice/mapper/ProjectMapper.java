package com.cholnhial.projectservice.mapper;

import com.cholnhial.projectservice.model.Project;
import com.cholnhial.projectservice.payload.ArtefactResponse;
import com.cholnhial.projectservice.payload.ProjectResponse;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    public ProjectResponse projectToProjectResponse(Project project) {
        ArtefactResponse artefactResponse = new ArtefactResponse();
        artefactResponse.setId(project.getArtefact().getId());
        artefactResponse.setName(project.getArtefact().getName());
        artefactResponse.setDescription(project.getArtefact().getDescription());
        artefactResponse.setPrice(project.getArtefact().getPrice());
        artefactResponse.setIsListed(project.getArtefact().getIsListed());
        artefactResponse.setPicture(project.getArtefact().getPicture());
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setTitle(project.getTitle());
        projectResponse.setStatus(project.getStatus());
        projectResponse.setId(project.getId());
        projectResponse.setUserId(project.getUserId());
        projectResponse.setDescription(project.getDescription());
        projectResponse.setArtefact(artefactResponse);
        return projectResponse;
    }
}

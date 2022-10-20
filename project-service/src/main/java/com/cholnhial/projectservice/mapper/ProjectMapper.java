package com.cholnhial.projectservice.mapper;

import com.cholnhial.projectservice.model.Collaborator;
import com.cholnhial.projectservice.model.Project;
import com.cholnhial.projectservice.payload.ArtefactResponse;
import com.cholnhial.projectservice.payload.CollaboratorResponse;
import com.cholnhial.projectservice.payload.ProjectResponse;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {


    public CollaboratorResponse collaboratorToCollaboratorResponse(Collaborator col) {
        CollaboratorResponse res = new CollaboratorResponse();
        res.setId(col.getId());
        res.setFullName(col.getFullName());
        res.setMobile(col.getMobile());
        res.setEmail(col.getEmail());
        res.setSuburb(col.getSuburb());
        res.setBio(col.getBio());
        res.setUserId(col.getUserId());

        return res;
    }
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
        projectResponse.setIsComplete(project.getIsComplete());
        projectResponse.setId(project.getId());
        projectResponse.setUserId(project.getUserId());
        projectResponse.setDescription(project.getDescription());
        projectResponse.setShortDescription(project.getShortDescription());
        projectResponse.setArtefact(artefactResponse);
        projectResponse.setCollaborators(project.getCollaborators().stream()
                .map(this::collaboratorToCollaboratorResponse).toList());

        return projectResponse;
    }
}

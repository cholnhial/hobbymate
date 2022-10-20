package com.cholnhial.projectservice.service;

import com.cholnhial.projectservice.model.Artefact;
import com.cholnhial.projectservice.model.Collaborator;
import com.cholnhial.projectservice.model.Project;
import com.cholnhial.projectservice.payload.UserResponse;
import com.cholnhial.projectservice.repository.CollaboratorRepository;
import com.cholnhial.projectservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final CollaboratorRepository collaboratorRepository;

    private final LoadBalancerClient loadBalancerClient;

    private final RestTemplate restTemplate;

    private void createArtefactForProject(Project project) {
        Artefact projectArtefact = new Artefact();
        projectArtefact.setPrice(new BigDecimal("0.00"));
        projectArtefact.setName(project.getTitle());
        projectArtefact.setDescription("-- DESCRIPTION HERE --");
        projectArtefact.setPicture("https://picsum.photos/200");
        projectArtefact.setIsListed(false);
        project.setArtefact(projectArtefact);
    }

    @Transactional
    public Project saveNewProject(Project project) {
        createArtefactForProject(project);
        return this.projectRepository.save(project);
    }

    @Transactional
    public void addCollaborator(Long projectId, Long userId) {
        ServiceInstance userServiceInstance = loadBalancerClient.choose("HOBBYMATE-USER-SERVICE");
        String url = userServiceInstance.getUri().toString() + "/users/api/user/" + userId;
        ResponseEntity<UserResponse> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, UserResponse.class);
        projectRepository.findById(projectId).ifPresent(project -> {
            if (response.getBody() != null) {
                Collaborator collaborator = new Collaborator();
                collaborator.setProject(project);
                collaborator.setBio(response.getBody().getBio());
                collaborator.setFullName(response.getBody().getFullName());
                collaborator.setSuburb(response.getBody().getSuburb());
                collaborator.setEmail(response.getBody().getEmail());
                collaborator.setUserId(response.getBody().getId());
                collaborator.setMobile(response.getBody().getMobile());
                collaboratorRepository.save(collaborator);
            }
        });
    }

    @Transactional
    public Project updateProject(Project project) {
        return this.projectRepository.save(project);
    }

    public List<Project> getAll() {
        return this.projectRepository.findAll();
    }

    public List<Project> getAllByUserId(Long userId) {
        return this.projectRepository.findAllByUserId(userId);
    }

    public Optional<Project> getOneById(Long id) {
        return this.projectRepository.findById(id);
    }
}

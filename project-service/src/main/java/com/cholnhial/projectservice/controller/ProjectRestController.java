package com.cholnhial.projectservice.controller;

import com.cholnhial.projectservice.mapper.ProjectMapper;
import com.cholnhial.projectservice.model.Project;
import com.cholnhial.projectservice.payload.NewProjectRequest;
import com.cholnhial.projectservice.payload.NewProjectResponse;
import com.cholnhial.projectservice.payload.ProjectResponse;
import com.cholnhial.projectservice.payload.ProjectUpdateRequest;
import com.cholnhial.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects/api")
@RequiredArgsConstructor
public class ProjectRestController {

    private final ProjectService projectService;

    private final ProjectMapper projectMapper;

    @PostMapping("/new")
    public ResponseEntity<NewProjectResponse> createProject(@Valid @RequestBody NewProjectRequest projectRequest) {
        Project project = new Project();
        project.setTitle(projectRequest.getTitle());
        project.setDescription(projectRequest.getDescription());
        project.setUserId(projectRequest.getUserId());
        project.setIsComplete(false);
        project.setShortDescription(projectRequest.getShortDescription());
        Project savedProject = projectService.saveNewProject(project);
        NewProjectResponse savedResponse = new NewProjectResponse(savedProject.getId(), savedProject.getTitle(),
                savedProject.getDescription(), savedProject.getShortDescription());
        return ResponseEntity.created(URI.create("/api/project/" + savedProject.getId())).body(savedResponse);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectResponse> getOneById(@PathVariable("id") Long id) {
        Optional<Project> projectOptional = projectService.getOneById(id);
        return projectOptional
                .map(p -> ResponseEntity.ok().body(projectMapper.projectToProjectResponse(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponse>> getAll() {
        return ResponseEntity.ok(this.projectService.getAll()
                .stream().map(projectMapper::projectToProjectResponse).toList());

    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<ProjectResponse>> getAllByUserId(@PathVariable("userId")  Long userId) {
        return ResponseEntity.ok(this.projectService.getAllByUserId(userId)
                .stream().map(projectMapper::projectToProjectResponse).toList());
    }

    @GetMapping("/all/{userId}/new")
    public ResponseEntity<List<ProjectResponse>> getAllNotOwnedByUserId(@PathVariable("userId")  Long userId) {
        return ResponseEntity.ok(this.projectService.getAllNotOwnedByUserId(userId)
                .stream().map(projectMapper::projectToProjectResponse).toList());
    }

    @GetMapping("/all/{userId}/collab")
    public ResponseEntity<List<ProjectResponse>> getAllCollabs(@PathVariable("userId")  Long userId) {
        return ResponseEntity.ok(this.projectService.getAllProjectsCollaboratingIn(userId)
                .stream().map(projectMapper::projectToProjectResponse).toList());
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@Valid @RequestBody ProjectUpdateRequest updateRequest) {
        Optional<Project> projectOptional = projectService.getOneById(updateRequest.getId());
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setTitle(updateRequest.getTitle());
            project.setDescription(updateRequest.getDescription());
            project.setIsComplete(updateRequest.getIsCompleted());
            project.getArtefact().setName(updateRequest.getArtefact().getName());
            project.getArtefact().setDescription(updateRequest.getArtefact().getDescription());
            project.getArtefact().setPrice(updateRequest.getArtefact().getPrice());
            project.getArtefact().setPicture(updateRequest.getArtefact().getPicture());
            project.getArtefact().setIsListed(updateRequest.getArtefact().getIsListed());

            return ResponseEntity.ok().body(projectMapper
                    .projectToProjectResponse(this.projectService.updateProject(project)));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/join/{projectId}/{userId}")
    public ResponseEntity<?> joinProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        // talk to users microservice to retrieve user details
      projectService.addCollaborator(projectId, userId);
      return  ResponseEntity.ok().build();
    }
}

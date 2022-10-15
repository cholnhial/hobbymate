package com.cholnhial.projectservice.controller;

import com.cholnhial.projectservice.mapper.ProjectMapper;
import com.cholnhial.projectservice.model.Project;
import com.cholnhial.projectservice.payload.NewProjectRequest;
import com.cholnhial.projectservice.payload.NewProjectResponse;
import com.cholnhial.projectservice.payload.ProjectResponse;
import com.cholnhial.projectservice.payload.ProjectUpdateRequest;
import com.cholnhial.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectRestController {

    private final ProjectService projectService;

    private final ProjectMapper projectMapper;

    @PostMapping("/project")
    public ResponseEntity<NewProjectResponse> createProject(@Valid @RequestBody NewProjectRequest projectRequest) {
        Project project = new Project();
        project.setTitle(projectRequest.getTitle());
        project.setDescription(project.getDescription());
        project.setUserId(projectRequest.getUserId());
        Project savedProject = projectService.saveNewProject(project);
        NewProjectResponse savedResponse = new NewProjectResponse(savedProject.getId(), savedProject.getTitle(),
                savedProject.getTitle());
        return ResponseEntity.created(URI.create("/api/project/" + savedProject.getId())).body(savedResponse);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectResponse> getOneById(@PathVariable("id") Long id) {
        Optional<Project> projectOptional = projectService.getOneById(id);
        return projectOptional
                .map(p -> ResponseEntity.ok().body(projectMapper.projectToProjectResponse(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/project/all")
    public ResponseEntity<List<ProjectResponse>> getAll() {
        return ResponseEntity.ok(this.projectService.getAll()
                .stream().map(projectMapper::projectToProjectResponse).toList());

    }

    @PutMapping("/project/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@Valid @RequestBody ProjectUpdateRequest updateRequest) {
        Optional<Project> projectOptional = projectService.getOneById(updateRequest.getId());
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setTitle(updateRequest.getTitle());
            project.setDescription(updateRequest.getDescription());
            project.setStatus(updateRequest.getStatus());
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

    @PostMapping("/project/{projectId}/{userId}")
    public ResponseEntity<?> joinProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        // talk to users microservice to retrieve user details

      return  ResponseEntity.ok().build();
    }
}

import { Component, OnInit } from '@angular/core';
import { faPlus, faEye, faEdit } from '@fortawesome/free-solid-svg-icons';
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {EntityArrayResponseType, ProjectsService} from "../projects.service";
import {IProject} from "../project.model";
import {INewProject} from "./new-project.model";
import * as _ from 'lodash';
import {IUpdateProject} from "./update-project.model";
import {IUpdateArtefact} from "./update-artefact.model";
import {PrincipalService} from "../principal.service";

@Component({
  selector: 'app-my-projects',
  templateUrl: './my-projects.component.html',
  styleUrls: ['./my-projects.component.scss']
})
export class MyProjectsComponent implements OnInit {
  closeResult = '';
  faPlus = faPlus;
  faEye = faEye;
  faEdit = faEdit;

  project:INewProject = {userId: 1, title: '', description: '', shortDescription: ''};
  myProjects: IProject[][] = [];
  originals: IProject[] = [];
  projectSelected: IUpdateProject = {
    artefact: {},
    description: "",
    id: 0,
    isCompleted: false,
    shortDescription: "",
    title: ""
  }

  constructor(private modalService: NgbModal, private projectService: ProjectsService, private principal: PrincipalService) {
  }

  ngOnInit(): void {
    this.loadMyProjects();
  }

  openNewProjectModal(content:any) {
    this.modalService.open(content, {fullscreen: true}).result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      },
    );
  }

  setSelectedProjectTo(project: IProject) {
    this.projectSelected.title = project.title;
    this.projectSelected.shortDescription = project.shortDescription;
    this.projectSelected.id = project.id;
    this.projectSelected.isCompleted = project.isComplete;
    this.projectSelected.description = project.description;
    this.projectSelected.artefact.name = project.artefact.name;
    this.projectSelected.artefact.isListed = project.artefact.isListed;
    this.projectSelected.artefact.price  = project.artefact.price;
    this.projectSelected.artefact.picture = project.artefact.picture;
    this.projectSelected.artefact.description = project.artefact.description;
  }

  openProjectUpdateModal(content:any, project: IProject) {
    this.setSelectedProjectTo(project);
    this.modalService.open(content, {fullscreen: true}).result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      },
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  loadMyProjects() {
    this.principal.identity(false).then(user => {
      this.projectService.getAllUserProjects(user.id).subscribe({
        next: (resp:any) => {
          this.originals = [...resp.body];
          this.myProjects = _.chunk(resp.body,3);
        }
      })
    })
  }
  onSaveNew(modal: any) {
    modal.close('');
    this.principal.identity(false).then(user => {
      this.project.userId = user.id;
      this.projectService.createNewProject(this.project).subscribe({
        next: (resp) => {
          this.loadMyProjects()
        }
      });
    });

  }

  onUpdate(modal: any) {
    modal.close('');
    this.projectService.updateProject(this.projectSelected).subscribe({
      next: (resp) => {
        this.loadMyProjects()
      }
    })
  }

  onDescriptionUpdated(update: any) {
    this.project.description = update.html;
  }

  onSelectedProjectContentChanged(update: any) {
    this.projectSelected.description = update.html;
  }

  onSearch(query: string) {
    if (query === '') {
      this.myProjects = _.chunk(this.originals, 3);
      return;
    }
    this.myProjects = _.chunk(this.originals.filter(l => l.title?.toLowerCase().includes(query.toLowerCase()) ||  l.shortDescription?.toLowerCase().includes(query.toLowerCase())), 3);
  }



}

import { Component, OnInit } from '@angular/core';
import * as _ from "lodash";
import {INewProject} from "../my-projects/new-project.model";
import {IProject} from "../project.model";
import {IUpdateProject} from "../my-projects/update-project.model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ProjectsService} from "../projects.service";
import { faPlus, faEye, faEdit } from '@fortawesome/free-solid-svg-icons';
import {PrincipalService} from "../principal.service";

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.scss']
})
export class JoinComponent implements OnInit {
  closeResult = '';
  faPlus = faPlus;
  faEye = faEye;
  faEdit = faEdit;

  newProjects: IProject[][] = [];
  originals: IProject[] = [];

  constructor( private projectService: ProjectsService, private principalService: PrincipalService) {
  }

  ngOnInit(): void {
    this.loadMyProjects();
  }

  loadMyProjects() {
    this.principalService.identity(false).then(user => {
      this.projectService.getAllNotJoined(user.id).subscribe({
        next: (resp:any) => {
          this.originals = [...resp.body];
          this.newProjects = _.chunk(resp.body,3);
        }
      });
    });
  }

  onSearch(query: string) {
    if (!query || query === '') {
      this.newProjects = _.chunk(this.originals, 3);
      return;
    }
    this.newProjects = _.chunk(this.originals.filter(l => l.title?.toLowerCase().includes(query.toLowerCase()) ||  l.shortDescription?.toLowerCase().includes(query.toLowerCase())), 3);
  }

}

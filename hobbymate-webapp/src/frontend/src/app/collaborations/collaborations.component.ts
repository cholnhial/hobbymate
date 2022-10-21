import { Component, OnInit } from '@angular/core';
import { faPlus, faEye, faEdit } from '@fortawesome/free-solid-svg-icons';
import {IProject} from "../project.model";
import * as _ from "lodash";
import {ProjectsService} from "../projects.service";
import {PrincipalService} from "../principal.service";

@Component({
  selector: 'app-collaborations',
  templateUrl: './collaborations.component.html',
  styleUrls: ['./collaborations.component.scss']
})
export class CollaborationsComponent implements OnInit {
  faPlus = faPlus;
  faEye = faEye;
  faEdit = faEdit;
  collabs: IProject[][] = [];
  originals: IProject[] = [];

  constructor(private projectService: ProjectsService, private principal: PrincipalService) { }

  ngOnInit(): void {
    this.loadMyCollabs();
  }

  loadMyCollabs() {
    this.principal.identity(false).then(user => {
      this.projectService.getAllCollabs(user.id).subscribe({
        next: resp => {
          this.collabs = _.chunk(resp.body,3);
        }
      })
    })
  }

  onSearch(e:any) {
    let query: any =  e.target.data;
    if (query === '') {
      this.collabs = _.chunk(this.originals, 3);
      return;
    }
    this.collabs = _.chunk(this.originals.filter(l => l.title?.toLowerCase().includes(query.toLowerCase()) ||  l.shortDescription?.toLowerCase().includes(query.toLowerCase())), 3);
  }

}

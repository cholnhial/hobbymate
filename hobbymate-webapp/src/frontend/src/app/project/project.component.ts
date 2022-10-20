import { Component, OnInit } from '@angular/core';
import { faCheckCircle, faClock } from '@fortawesome/free-solid-svg-icons';
import {ProjectsService} from "../projects.service";
import {ActivatedRoute} from "@angular/router";
import {IProject} from "../project.model";
import {PrincipalService} from "../principal.service";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
  faCheckCircle = faCheckCircle;
  faClock = faClock;
  project: any;

  constructor(private projectService: ProjectsService,private  principal: PrincipalService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.projectService.getProjectById(params['projectId']).subscribe({
        next: resp => {
          this.project = resp.body;
        }
      })
    })
  }

  onJoin() {
    this.principal.identity(false).then(user => {
      this.projectService.join(this.project.id, user.id).subscribe();
    });

  }

}

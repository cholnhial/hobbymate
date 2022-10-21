import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {INewProject} from "./my-projects/new-project.model";
import {Observable} from "rxjs";
import {SERVER_API_URL} from "./app.constants";
import {IProject} from "./project.model";
import {IUpdateProject} from "./my-projects/update-project.model";
import {getServerAddress} from "./utils";

export type EntityResponseType = HttpResponse<IProject>;
export type EntityArrayResponseType = HttpResponse<IProject[]>;

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  constructor(public http: HttpClient) { }


  createNewProject(project: INewProject): Observable<HttpResponse<any>> {
    return this.http.post(getServerAddress() + 'projects/api/new', project, {observe: 'response'});
  }

  join(projectId: number, userId: number): Observable<HttpResponse<any>> {
    return this.http.post(getServerAddress() + `projects/api/join/${projectId}/${userId}`, {}, {observe: 'response'});
  }

  getAllUserProjects(userId: number): Observable<EntityArrayResponseType> {
    return this.http.get<IProject[]>(getServerAddress() + `projects/api/all/${userId}`, { observe: 'response' });
  }

  getAllNotJoined(userId: number): Observable<EntityArrayResponseType> {
    return this.http.get<IProject[]>(getServerAddress() + `projects/api/all/${userId}/new`, { observe: 'response' });
  }

  getAllCollabs(userId: number): Observable<EntityArrayResponseType> {
    return this.http.get<IProject[]>(getServerAddress() + `projects/api/all/${userId}/collab`, { observe: 'response' });
  }

  updateProject(project: IUpdateProject): Observable<HttpResponse<any>> {
    return this.http.put(getServerAddress() + `projects/api/project/${project.id}`, project, {observe: 'response'});
  }

  getProjectById(projectId: number): Observable<HttpResponse<any>> {
    return this.http.get<any>(getServerAddress() + `projects/api/project/${projectId}`, {observe: 'response'});
  }


}

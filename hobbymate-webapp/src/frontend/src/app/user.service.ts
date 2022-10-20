import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {SERVER_API_URL} from "./app.constants";
import {IUser} from "./user.model";
import {LocalStorageService} from "ngx-webstorage";
import {INewProject} from "./my-projects/new-project.model";
import {INewRegistration} from "./new-registration.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private $localStorage: LocalStorageService) {}

  register(details: INewRegistration): Observable<HttpResponse<any>> {
    return this.http.post(`http://localhost:8093/users/api/register`, details, {observe: 'response'});
  }

  performLogin(email: string): Observable<any> {
    return this.http.get(`http://localhost:8093/users/api/login/${email}`,{ observe: 'response' }).pipe(map(authenticateSuccess.bind(this)));

    function authenticateSuccess(resp: any) {
      this.saveUserAccount(resp.body);
      return resp.body;
    }
  }

  saveUserAccount(account: any) {
    this.$localStorage.store('user', account);
  }

  performLogout(): Observable<any> {
    return new Observable(observer => {
      this.$localStorage.clear('user');
      observer.complete();
    });
  }

}

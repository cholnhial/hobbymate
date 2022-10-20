import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {LocalStorageService} from "ngx-webstorage";

@Injectable({ providedIn: 'root' })
export class PrincipalService {
  private loggedInUserIdentity: any;
  private authenticated = false;
  private authState = new Subject<any>();

  constructor(private $localStorage: LocalStorageService) {}

  authenticate(identity: any) {
    this.loggedInUserIdentity = identity;
    this.authenticated = identity !== null;
    this.authState.next(this.loggedInUserIdentity);
  }

  identity(force?: boolean): Promise<any> {
    if (force === true) {
      this.loggedInUserIdentity = undefined;
    }

    if (this.loggedInUserIdentity) {
      return Promise.resolve(this.loggedInUserIdentity);
    }


   return Promise.resolve(this.$localStorage.retrieve("user"))
     .then(user => {
      const userAccount = user;
      if (userAccount) {
        this.loggedInUserIdentity = userAccount;
        this.authenticated = true;
      } else {
        this.loggedInUserIdentity = null;
        this.authenticated = false;
      }
      this.authState.next(this.loggedInUserIdentity);
      return this.loggedInUserIdentity;
    })
      .catch(err => {
        // won't occur
      });


  }

  isAuthenticated(): boolean {
    return this.authenticated;
  }

  isIdentityResolved(): boolean {
    return this.loggedInUserIdentity !== undefined;
  }

  getAuthenticationState(): Observable<any> {
    return this.authState.asObservable();
  }

}

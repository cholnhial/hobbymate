

import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {PrincipalService} from "./principal.service";

@Injectable({ providedIn: 'root' })
export class UserRouteAccessService implements CanActivate {
  constructor(
    private router: Router,
    private principalService: PrincipalService
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Promise<boolean> {
    return this.checkLogin(state.url);
  }

  checkLogin(url: string): Promise<boolean> {
    const principal = this.principalService;
    return Promise.resolve(
      principal.identity().then(account => {

        if (account) {
          return true; // user has logged in
        }
        this.router.navigateByUrl('/login');
        return false;
      })
    );
  }
}

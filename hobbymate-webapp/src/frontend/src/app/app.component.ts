import { Component } from '@angular/core';
import {UserService} from "./user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  constructor(private userService: UserService, private router: Router) {
  }

  logout() {
    this.userService.performLogout().subscribe();
    this.router.navigateByUrl("/login");
  }

}

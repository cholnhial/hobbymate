import {Component, OnInit} from '@angular/core';
import {UserService} from "./user.service";
import {Router} from "@angular/router";
import {PrincipalService} from "./principal.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'frontend';
  user: any;
  constructor(private userService: UserService, private principal: PrincipalService, private router: Router) {
  }


  logout() {
    this.userService.performLogout().subscribe();
    this.router.navigateByUrl("/login");
  }

  ngOnInit(): void {
    this.principal.identity(false).then(user => {
      this.user = user;
    })
  }

}

import {Component, OnInit} from '@angular/core';
import {UserService} from "./user.service";
import {NavigationStart, Router} from "@angular/router";
import {PrincipalService} from "./principal.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'frontend';
  showHead: boolean = false;

  constructor(private userService: UserService, public principal: PrincipalService, private router: Router) {
    router.events.forEach((event) => {
      if (event instanceof NavigationStart) {
        if (event['url'] == '/login' || event['url'] == '/register') {
          this.showHead = false;
        } else {
          this.showHead = true;
        }
      }
    });
  }



  logout() {
    this.userService.performLogout().subscribe();
    this.router.navigateByUrl("/login");
  }

  ngOnInit(): void {

  }

}

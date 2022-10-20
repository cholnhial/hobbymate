import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public emailAddress = '';

  constructor(public router: Router, public userService: UserService) { }

  ngOnInit(): void {
  }

  onLogin() {
    this.userService.performLogin(this.emailAddress).subscribe({
      next: (resp) => {
        this.router.navigateByUrl("/my-projects");
      }
    })
  }

}

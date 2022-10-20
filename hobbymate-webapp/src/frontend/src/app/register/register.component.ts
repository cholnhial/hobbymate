import { Component, OnInit } from '@angular/core';
import {INewRegistration} from "../new-registration.model";
import {UserService} from "../user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  newUser: INewRegistration = {bio: "", fullName: "", email: "", mobile: "", suburb: ""};

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
  }

  onRegister() {
    this.userService.register(this.newUser).subscribe({
      next: resp => {
        this.router.navigateByUrl('/login');
      }
    })
  }
}

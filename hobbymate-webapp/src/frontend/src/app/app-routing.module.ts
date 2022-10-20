import { NgModule } from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {MyProjectsComponent} from "./my-projects/my-projects.component";
import {ProjectComponent} from "./project/project.component";
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {UserRouteAccessService} from "./user-route-access.service";
import {JoinComponent} from "./join/join.component";
import {ShopComponent} from "./shop/shop.component";
import {CollaborationsComponent} from "./collaborations/collaborations.component";


const routes: Routes = [
  {
    path: '',
    redirectTo: 'my-projects',
    pathMatch: 'full'
  },
  {
    path: 'my-projects',
    component: MyProjectsComponent,
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'project/:projectId',
    component: ProjectComponent,
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'join',
    component: JoinComponent,
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'collab',
    component: CollaborationsComponent,
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'shop',
    component: ShopComponent,
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

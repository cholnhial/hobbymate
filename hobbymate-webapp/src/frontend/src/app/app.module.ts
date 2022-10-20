import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MyProjectsComponent } from './my-projects/my-projects.component';
import { CollaborationsComponent } from './collaborations/collaborations.component';
import { ShopComponent } from './shop/shop.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {QuillModule} from "ngx-quill";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { ProjectComponent } from './project/project.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import {NgxWebstorageModule} from "ngx-webstorage";
import { JoinComponent } from './join/join.component';

@NgModule({
  declarations: [
    AppComponent,
    MyProjectsComponent,
    CollaborationsComponent,
    ShopComponent,
    ProjectComponent,
    RegisterComponent,
    LoginComponent,
    JoinComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    QuillModule.forRoot(),
    NgbModule,
    FormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

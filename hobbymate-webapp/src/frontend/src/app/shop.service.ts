import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {IProject} from "./project.model";
import {INewProject} from "./my-projects/new-project.model";
import {Observable} from "rxjs";
import {SERVER_API_URL} from "./app.constants";
import {IUpdateProject} from "./my-projects/update-project.model";
import {IShop} from "./shop.model";
import {getServerAddress} from "./utils";

export type EntityResponseType = HttpResponse<IShop>;
export type EntityArrayResponseType = HttpResponse<IShop[]>;

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(public http: HttpClient) { }

  getAll(): Observable<EntityArrayResponseType> {
    return this.http.get<IShop[]>(getServerAddress() + "shop/api/all/", { observe: 'response' });
  }

}

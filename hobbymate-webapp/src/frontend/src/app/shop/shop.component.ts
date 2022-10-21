import { Component, OnInit } from '@angular/core';
import {IProject} from "../project.model";
import {IShop} from "../shop.model";
import * as _ from "lodash";
import {ProjectsService} from "../projects.service";
import {ShopService} from "../shop.service";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {

  listings: IShop[][] = [];
  original: IShop[]= [];
  constructor(private shopService: ShopService) { }

  ngOnInit(): void {
    this.loadListings();
  }

  loadListings() {
    this.shopService.getAll().subscribe({
      next: (resp:any) => {
        this.listings = _.chunk(resp.body,3);
        this.original = [...resp.body];
      }
    })
  }

  onSearch(query: string) {
    if (query === '') {
      this.listings = _.chunk(this.original, 3);
      return;
    }
    this.listings = _.chunk(this.original.filter(l => l.name.toLowerCase().includes(query.toLowerCase()) ||  l.description.toLowerCase().includes(query.toLowerCase())), 3);
  }
}

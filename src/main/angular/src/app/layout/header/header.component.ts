import { Component, OnInit } from '@angular/core';
import { LocalStorage } from "../../core/common/storage/LocalStorage";
import { Router } from "@angular/router";

@Component({
  selector: 'layout-header',
  templateUrl: './header.component.html',
  styleUrls: [ './header.component.less' ]
})
export class HeaderComponent implements OnInit {

  user: any;

  selectedLi = -1;

  constructor(
    private ls: LocalStorage,
    private router: Router
  ) {
  }

  ngOnInit() {
    if (this.router.url === '/gallery/create') {
      this.selectedLi = 0;
    }

    this.user = this.ls.getObject('user');
    if (this.user === null) {
      this.router.navigate([ 'passport' ]);
    }
  }

  logout(): void {
    this.ls.remove('token');
    this.ls.remove('user');
    this.router.navigate([ 'passport' ]);
  }

}

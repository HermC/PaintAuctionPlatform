import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { NzMessageService } from "ng-zorro-antd";
import { ActivatedRoute } from "@angular/router";
import { environment } from "../../../environments/environment";

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: [ './info.component.less' ]
})
export class InfoComponent implements OnInit {

  lot: any;

  lotImgs: [];

  quote: number;

  constructor(
    private http: HttpClient,
    private message: NzMessageService,
    private route: ActivatedRoute
  ) {
    this.lot = null;
  }

  ngOnInit() {
    this.route.queryParams.subscribe(queryParams => {
      if (!queryParams['id']) {
        this.message.error('该拍卖品不存在!');
        return;
      }
      this.getLot(queryParams['id']);
    });
  }

  getLot(id: number): void {
    this.http.get(`api/lot/${id}`, {
      withCredentials: false
    })
    .subscribe(res => {
      console.log(res);
      if (!res['success']) {
        this.message.error(res['message']);
      } else {
        this.lot = res['data']['lot'];
        this.lotImgs = this.lot.lotImgs;
        console.log(this.lotImgs);
        this.quote = this.lot.startingPrice;
      }
    }, (error: HttpErrorResponse) => {

    }, () => {

    });
  }

  getFullUrl(imageName): string {
    return `${environment.base_url}images/${imageName}`;
  }

  renderTime(date) {
    let dateee = new Date(date).toJSON();
    return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
  }

  formatterDollar = value => `￥ ${value}`;

}

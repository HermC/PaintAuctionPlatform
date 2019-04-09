import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { NzMessageService } from "ng-zorro-antd";
import { environment } from "../../../environments/environment";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: [ './main.component.less' ]
})
export class MainComponent implements OnInit {

  lots: Lot[];

  nextPage = 0;
  pageSize = 12;
  totalPages = null;

  constructor(
    private http: HttpClient,
    private message: NzMessageService
  ) {
    this.lots = [];
  }

  ngOnInit() {
    this.getProcessingLots(this.nextPage, this.pageSize);

    // 滚动事件触发
    window.onscroll = () => {
      if (this.getScrollTop() + this.getClientHeight() === this.getScrollHeight()) {
        console.log('下拉刷新了');
        if (this.totalPages !== null && this.nextPage >= this.totalPages) {
          return;
        }
        this.getProcessingLots(this.nextPage, this.pageSize);
      }
    }
  }

  getProcessingLots(page, size): void {
    this.http.get(`api/lot/processing/list?page=${page}&size=${size}`, {
      withCredentials: false
    })
      .subscribe(res => {
        console.log(res);
        if (!res[ 'success' ]) {
          this.message.error(res[ 'message' ]);
        } else {
          const currentPage = res[ 'data' ][ 'currentPage' ];
          const totalPages = res[ 'data' ][ 'totalPages' ];
          const lots: Lot[] = res[ 'data' ][ 'lots' ];
          if (currentPage === this.nextPage) {
            this.nextPage = currentPage + 1;
            this.lots = this.lots.concat(lots);
          }
          console.log(this.lots);
          this.totalPages = totalPages;
        }
      }, (error: HttpErrorResponse) => {
        this.handleError(error);
      }, () => {

      });
  }

  // 获取滚动条
  getScrollTop(): any {
    let scrollTop = 0;
    if (document.documentElement && document.documentElement.scrollTop) {
      scrollTop = document.documentElement.scrollTop;
    } else if (document.body) {
      scrollTop = document.body.scrollTop;
    }
    return scrollTop;
  }

  // 获取当前可视范围的高度
  getClientHeight(): any {
    let clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
      clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
    } else {
      clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
    }
    return clientHeight;
  }

  // 获取文档完整的高度
  getScrollHeight(): any {
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
  }

  private handleError(error: HttpErrorResponse): void {
    if (error.error && error.error[ 'message' ]) {
      this.message.error(error.error[ 'message' ]);
    } else {
      this.message.error(error.message);
    }
  }

  getFullUrl(imageName): string {
    return `${environment.base_url}images/${imageName}`;
  }
}



export interface Lot {
  id: number;
  name: string;
  description: string;
  startingPrice: number;
  startingTime: any;
  endingPrice: number;
  endingTime: any;
  lotImgs: any[];
}

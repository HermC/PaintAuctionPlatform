import { Injectable, Injector } from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import { Observable } from "rxjs";
import { LocalStorage } from "../storage/LocalStorage";
import { NzMessageService } from "ng-zorro-antd";
import { Router } from "@angular/router";
import { environment } from "../../../../environments/environment";

/**什么也不做，只是简单的转发请求而不做任何修改*/
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private injector: Injector,
    private ls: LocalStorage
  ) { }

  get msg(): NzMessageService {
    return this.injector.get(NzMessageService);
  }

  private goTo(url: string) {
    setTimeout(() => this.injector.get(Router).navigateByUrl(url));
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    const token = this.ls.get('token');
    // console.log(token);
    if (!token) {
      if (!(req.url.startsWith('api/auth/token') || req.url.startsWith('api/user/register') || req.url.indexOf('assets/') >= 0)) {
        this.goTo('passport');
      }
    }

    let url = req.url;
    if (!url.startsWith('https://') && !url.startsWith('http://') && url.indexOf('assets/') < 0) {
      url = environment.base_url + url;
    }
    let newReq = req.clone({
      url: url
    });

    // console.log(newReq);

    if (token) {
      newReq = newReq.clone({
        setHeaders: {
          Authorization: `${token}`
        }
      });
    }

    return next.handle(newReq);
  }

}

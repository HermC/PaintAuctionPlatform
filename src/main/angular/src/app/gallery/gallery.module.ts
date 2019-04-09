import { NgModule } from '@angular/core';
import { SharedModule } from "../shared/shared.module";
import { MainComponent } from './main/main.component';
import { GalleryRoutingModule } from './gallery-routing.module';
import { LocalStorage } from "../core/common/storage/LocalStorage";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { AuthInterceptor } from "../core/common/interceptor/AuthInterceptor";
import { InfoComponent } from './info/info.component';
import { CreateComponent } from './create/create.component';

@NgModule({
  imports: [
    SharedModule,
    GalleryRoutingModule,
  ],
  declarations: [MainComponent, InfoComponent, CreateComponent],
  providers: [
    LocalStorage,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ]
})
export class GalleryModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DefaultComponent } from "./layout/default/default.component";
import { PassportComponent } from "./layout/passport/passport.component";

const routes: Routes = [
  {
    path: 'main',
    component: DefaultComponent,
    children: [
      {
        path: 'gallery', loadChildren: './gallery/gallery.module#GalleryModule'
      }
    ]
  },
  {
    path: '',
    redirectTo: 'passport',
    pathMatch: 'full'
  },
  {
    path: 'passport',
    component: PassportComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import { MainComponent } from "./main/main.component";
import { InfoComponent } from "./info/info.component";
import { CreateComponent } from "./create/create.component";

const routes: Routes = [
  { path: 'view', component: MainComponent },
  { path: 'info', component: InfoComponent },
  { path: 'create', component: CreateComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class GalleryRoutingModule { }

import { NgModule } from '@angular/core';
import { DefaultComponent } from './default/default.component';
import { HeaderComponent } from './header/header.component';
import { SharedModule } from "../shared/shared.module";
import { PassportComponent } from './passport/passport.component';

const COMPONENTS = [
  DefaultComponent,
  HeaderComponent,
  PassportComponent
];

@NgModule({
  declarations: [
    ...COMPONENTS,
  ],
  imports: [
    SharedModule
  ],
  exports: [
    ...COMPONENTS
  ]
})
export class LayoutModule { }

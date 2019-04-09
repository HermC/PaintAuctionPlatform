import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { NgZorroAntdModule } from "ng-zorro-antd";
import { HttpClientModule } from "@angular/common/http";

const MODULES = [
  CommonModule,
  FormsModule,
  RouterModule,
  HttpClientModule,
  ReactiveFormsModule
];

const THIRD_MODULES = [
  NgZorroAntdModule
];

const COMPONENTS = [];
const DIRECTIVES = [];

@NgModule({
  declarations: [
    ...COMPONENTS,
    ...DIRECTIVES
  ],
  imports: [
    ...MODULES,
    ...THIRD_MODULES
  ],
  exports: [
    ...MODULES,
    ...THIRD_MODULES,
    ...COMPONENTS,
    ...DIRECTIVES
  ]
})
export class SharedModule { }

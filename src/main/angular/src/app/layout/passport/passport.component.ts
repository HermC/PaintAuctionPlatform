import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { NzMessageService } from "ng-zorro-antd";
import { LocalStorage } from "../../core/common/storage/LocalStorage";
import { Router } from "@angular/router";

@Component({
  selector: 'app-passport',
  templateUrl: './passport.component.html',
  styleUrls: ['./passport.component.less']
})
export class PassportComponent implements OnInit {

  loginForm: FormGroup;
  registerForm: FormGroup;

  logining = false;
  registering = false;

  loginSuccessMsg = null;
  loginErrorMsg = null;
  registerSuccessMsg = null;
  registerErrorMsg = null;

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private ls: LocalStorage,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [ null, [ Validators.required ] ],
      password: [ null, [ Validators.required ] ],
    });
    this.registerForm = this.fb.group({
      username: [ null, [ Validators.required ] ],
      email: [ null, [ Validators.email, Validators.required ] ],
      password: [ null, [ Validators.required, Validators.minLength(6), Validators.maxLength(20) ] ],
      confirm: [ null, [ Validators.required, this.confirmationValidator ] ]
    });
  }

  duplicatorValidator = (control: FormControl): { [ s: string ]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else {
      // TODO
    }
  };

  confirmationValidator = (control: FormControl): { [ s: string ]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.registerForm.controls.password.value) {
      return { confirm: true, error: true };
    }
  };

  submitLoginForm(): void {
    for (const i in this.loginForm.controls) {
      this.loginForm.controls[ i ].markAsDirty();
      this.loginForm.controls[ i ].updateValueAndValidity();
    }
    if (this.loginForm.valid) {
      this.logining = true;
      this.loginSuccessMsg = null;
      this.loginErrorMsg = null;
      this.http.post('api/auth/token', this.loginForm.getRawValue(), {
        withCredentials: false
      })
      .subscribe(res => {
        if (!res['success']) {
          this.message.error(res['message']);
          this.loginErrorMsg = res['message'];
        } else {
          this.ls.set('token', res['data']['token']);
          this.ls.setObject('user', res['data']['user']);

          this.router.navigate(['main', 'gallery', 'view']);
        }
      }, (error: HttpErrorResponse) => {
        this.handleError(error);
      }, () => {
        this.logining = false;
      });
    }

  }

  submitRegisterForm(): void {
    for (const i in this.registerForm.controls) {
      this.registerForm.controls[ i ].markAsDirty();
      this.registerForm.controls[ i ].updateValueAndValidity();
    }
    if (this.registerForm.valid) {
      this.registering = true;
      this.http.post('api/user/register', this.registerForm.getRawValue(), {
        withCredentials: false
      })
      .subscribe(res => {
        if (!res['success']) {
          this.message.error(res['message']);
          this.registerErrorMsg = res['message'];
        } else {
          this.message.success('注册成功!');
          this.registerForm.reset();
          this.registerSuccessMsg = '注册成功，请前往登录!';
        }
      }, (error: HttpErrorResponse) => {
        this.handleError(error);
      }, () => {
        this.registering = false;
      });
    }
  }

  getErrors(errors): string {
    if (errors.required) {
      return '该项目不能为空!';
    }
    if (errors.email) {
      return '邮箱格式不正确!';
    }
    if (errors.duplicated) {
      return '用户名已存在!';
    }
    if (errors.minlength) {
      return `最小长度为${errors.minlength.requiredLength}, 当前长度为${errors.minlength.actualLength}。`;
    }
    if (errors.maxlength) {
      return `最大长度为${errors.maxlength.requiredLength}, 当前长度为${errors.maxlength.actualLength}。`;
    }
    if (errors.confirm && errors.error) {
      return `两次输入的密码不同!`;
    }
    return '';
  }

  private handleError(error: HttpErrorResponse): void {
    if (error.error && error.error['message']) {
      this.message.error(error.error['message']);
    } else {
      this.message.error(error.message);
    }
  }

}

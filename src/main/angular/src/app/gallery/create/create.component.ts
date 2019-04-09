import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import {
  HttpClient,
  HttpErrorResponse,
  HttpEvent,
  HttpEventType,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import { NzMessageService, UploadFile, UploadXHRArgs } from "ng-zorro-antd";
import { Router } from "@angular/router";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: [ './create.component.less' ]
})
export class CreateComponent implements OnInit {

  lotForm: FormGroup;

  imageList = [];

  previewImage = '';
  previewVisible = false;

  submitting = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private message: NzMessageService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.lotForm = this.fb.group({
      name: [ null, [ Validators.required ] ],
      description: [ null ],
      startingTime: [ null, [ Validators.required ] ],
      startingPrice: [ null, [ Validators.required ] ],
      endingTime: [ null, [ Validators.required ] ],
      imgSrcs: [ null, [ Validators.required ] ]
    });
  }

  customReq = (item: UploadXHRArgs) => {
    // 构建一个 FormData 对象，用于存储文件或其他参数
    const formData = new FormData();
    // tslint:disable-next-line:no-any
    formData.append('image', item.file as any);
    const req = new HttpRequest('POST', 'api/upload/image', formData, {
      reportProgress : true,
      withCredentials: false
    });
    // 始终返回一个 `Subscription` 对象，nz-upload 会在适当时机自动取消订阅
    return this.http.request(req).subscribe((event: HttpEvent<{}>) => {
      if (event.type === HttpEventType.UploadProgress) {
        if (event.total > 0) {
          // tslint:disable-next-line:no-any
          (event as any).percent = event.loaded / event.total * 100;
        }
        // 处理上传进度条，必须指定 `percent` 属性来表示进度
        item.onProgress(event, item.file);
      } else if (event instanceof HttpResponse) {
        // 处理成功
        if (!event.body['success']) {
          this.message.error(event.body['message']);
          item.onError(null, item.file);
        } else {
          item.onSuccess(event.body, item.file, event);
          this.message.success('上传成功!');
        }
      }
    }, (error: HttpErrorResponse) => {
      // 处理失败
      item.onError(error, item.file);
      this.handleError(error);
    });
  };

  handlePreview = (file: UploadFile) => {
    this.previewImage = file.url || file.thumbUrl;
    this.previewVisible = true;
  };

  submitLotForm(): void {
    if (this.imageList === null || this.imageList.length === 0) {
      this.message.error('请至少上传一张图片!');
      return;
    }
    const imgSrcs = [];
    for (let i = 0; i < this.imageList.length; i++) {
      if (this.imageList[i]['status'] === 'done') {
        imgSrcs.push(this.imageList[i]['response']['data']['imageName']);
      }
    }
    this.lotForm.patchValue({ imgSrcs: imgSrcs });
    for (const i in this.lotForm.controls) {
      this.lotForm.controls[ i ].markAsDirty();
      this.lotForm.controls[ i ].updateValueAndValidity();
    }
    console.log(this.lotForm.getRawValue());
    this.submitting = true;
    if (this.lotForm.valid) {
      this.http.post('api/lot/new', this.lotForm.getRawValue(), {
        withCredentials: false
      })
      .subscribe(res => {
        console.log(res);
        if (!res['success']) {
          this.message.error(res['message']);
        } else {
          this.message.success('提交成功!');
          // this.router.navigate(['gallery', 'view']);
          this.lotForm.reset();
          this.imageList = [];
        }
      }, (error: HttpErrorResponse) => {
        this.handleError(error);
        this.submitting = false;
      }, () => {
        this.submitting = false;
      });
    }
  }



  private handleError(error: HttpErrorResponse): void {
    if (error.error && error.error['message']) {
      this.message.error(error.error['message']);
    } else {
      this.message.error(error.message);
    }
  }

}

import { DeleteQuery } from './../../model/delete-query';
import { DeleteQueryService } from './../../providers/delete-query-service/delete-query-service';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertController } from 'ionic-angular';


@IonicPage()
@Component({
  selector: 'page-add-delete-query',
  templateUrl: 'add-delete-query.html',
})
export class AddDeleteQueryPage {

  public deleteQueryForm: FormGroup;

  constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder, public deleteQueryService: DeleteQueryService, public alertCtrl: AlertController) {
    this.initFormData();
  }

  public initFormData(): void {
    this.deleteQueryForm = this.formBuilder.group({
      field: ['', [Validators.required]],
      value: ['', [Validators.required]],
    });
  }
  public addDeleteQuery() {
    const deleteQuery = new DeleteQuery(this.deleteQueryForm.controls['field'].value, this.deleteQueryForm.controls['value'].value);
    this.deleteQueryService.addDeleteQuery(deleteQuery).subscribe(
      succ => { console.log('1'); this.navCtrl.pop(); },
      err => this.presentAlert()
    );

  }
  public presentAlert() {
    const alert = this.alertCtrl.create({
      title: 'Error',
      subTitle: 'Can\'t add new delete query',
      buttons: ['Ok']
    });
    alert.present();
  }
}

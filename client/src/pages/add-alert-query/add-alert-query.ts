import { IndexField } from './../../model/index-field-response';
import { IndexService } from './../../providers/index-service/index-service';
import { Alertquery } from './../../model/alert-query-response';
import { AlertQueryService } from './../../providers/alert-query-service/alert-query-service';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertController } from 'ionic-angular';


@IonicPage()
@Component({
  selector: 'page-add-alert-query',
  templateUrl: 'add-alert-query.html',
})
export class AddAlertQueryPage {

  public alertQueryForm: FormGroup;

  public indexFields: IndexField[] = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder, public alertQueryService: AlertQueryService, public alertCtrl: AlertController, public indexService: IndexService) {
    this.initFormData();
    this.initFieldNames();
  }

  public initFormData(): void {
    this.alertQueryForm = this.formBuilder.group({
      field: ['', [Validators.required]],
      value: ['', [Validators.required]],
    });
  }

  public initFieldNames(): void {
    this.indexService.getIndexFieldNames().subscribe(
      indexFields =>  {
        this.indexFields = indexFields;
        console.log(this.indexFields);
      });
  }
  p

  public addAlertQuery() {
    const alertQuery = <Alertquery>{ 'field': this.alertQueryForm.controls['field'].value, 'value': this.alertQueryForm.controls['value'].value };
    this.alertQueryService.addAlertQuery(alertQuery).subscribe(
      succ => this.navCtrl.pop(),
      err => this.presentAlert()
    );

  }
  public presentAlert() {
    const alert = this.alertCtrl.create({
      title: 'Error',
      subTitle: 'Can\'t add new alert query',
      buttons: ['Ok']
    });
    alert.present();
  }
}

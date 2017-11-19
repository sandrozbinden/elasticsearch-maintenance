import { IndexField } from './../../model/index-field-response';
import { IndexService } from './../../providers/index-service/index-service';
import { Deletequery } from './../../model/delete-query-response';
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

  public indexFields: IndexField[] = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder, public deleteQueryService: DeleteQueryService, public alertCtrl: AlertController, public indexService: IndexService) {
    this.initFormData();
    this.initFieldNames();
  }

  public initFormData(): void {
    this.deleteQueryForm = this.formBuilder.group({
      query: ['', [Validators.required]],
    });
  }

  public initFieldNames(): void {
    this.indexService.getIndexFieldNames().subscribe(
      indexFields =>  {
        this.indexFields = indexFields;
      });
  }
  p

  public addDeleteQuery() {
    const deleteQuery = <Deletequery>{ 'query': this.deleteQueryForm.controls['query'].value};
    this.deleteQueryService.addDeleteQuery(deleteQuery).subscribe(
      succ => this.navCtrl.pop(),
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

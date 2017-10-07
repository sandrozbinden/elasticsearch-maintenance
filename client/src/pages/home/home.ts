import { DeleteQuery } from './../../model/delete-query';
import { AddDeleteQueryPage } from './../add-delete-query/add-delete-query';
import { DeleteQueryService } from './../../providers/delete-query-service/delete-query-service';
import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  public deleteQueries: DeleteQuery[] = [];

  constructor(public navCtrl: NavController, public deleteQueryService: DeleteQueryService) {
    
  }

  public ionViewDidLoad(): void {
    this.deleteQueryService.getDeleteQueries().subscribe(
      deleteQueries => this.deleteQueries = deleteQueries.deleteQueries
    ); 
  }

  public addDeleteQuery() {
    this.navCtrl.push(AddDeleteQueryPage);
  }

}

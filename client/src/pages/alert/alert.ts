
import { Alertquery, Next } from './../../model/alert-query-response';
import { AddAlertQueryPage } from './../add-alert-query/add-alert-query';
import { AlertQueryService } from './../../providers/alert-query-service/alert-query-service';
import { Component } from '@angular/core';
import { NavController, InfiniteScroll } from 'ionic-angular';

@Component({
  selector: 'page-alert',
  templateUrl: 'alert.html',
})
export class AlertPage {


  public alertQueries: Alertquery[] = [];
  public nextPage: Next;

  constructor(public navCtrl: NavController, public alertQueryService: AlertQueryService) {

  }

  public ionViewWillEnter(): void {
    this.alertQueryService.getAlertQueries().subscribe(
      alertQueries =>  {
        this.alertQueries = alertQueries._embedded.alertQueries;
        this.nextPage = alertQueries._links.next;
      });
  }

  public addAlertQuery() {
    this.navCtrl.push(AddAlertQueryPage);
  }

  public removeAlertQuery(alertQuery: Alertquery) {
    this.alertQueryService.removeAlertQuery(alertQuery._links.self.href).subscribe(
      succ => this.alertQueries = this.alertQueries.filter(d => d !== alertQuery),
    );
  }

  public infiniteEntries(infiniteScroll: InfiniteScroll): void {
    if (this.nextPage === undefined) {
      infiniteScroll.enable(false);
    } else {
      this.alertQueryService.getNextAlertQueries(this.nextPage.href).subscribe(
        alertQueries => {
          this.alertQueries = this.alertQueries.concat(alertQueries._embedded.alertQueries);
          this.nextPage = alertQueries._links.next;
          infiniteScroll.complete();
        });
    }
  }

}

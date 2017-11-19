import { IndexService } from './../../providers/index-service/index-service';
import { Alert, Next } from './../../model/alert-response';
import { AlertService } from './../../providers/alert-service/alert-service';

import { Component } from '@angular/core';
import { NavController, InfiniteScroll } from 'ionic-angular';

@Component({
  selector: 'page-alert',
  templateUrl: 'alert.html',
})
export class AlertPage {


  public alerts: Alert[] = [];
  public nextPage: Next;
  public indiciesSize: number;

  constructor(public navCtrl: NavController, public alertService: AlertService, public indexService: IndexService) {

  }

  public ionViewWillEnter(): void {
    this.alertService.getAlerts().subscribe(
      alerts => {
        this.alerts = alerts._embedded.alerts.filter(alert => alert.alertStatus === 'OPEN');
        this.nextPage = alerts._links.next;
      });
    this.indexService.getIndciesSize().subscribe(
      indiciesSize => this.indiciesSize = indiciesSize
    );
  }

  public completeAlert(alert: Alert) {
    this.alertService.completeAlert(alert).subscribe(
      succ => this.alerts = this.alerts.filter(d => d !== alert)
    );
  }

  public infiniteEntries(infiniteScroll: InfiniteScroll): void {
    if (this.nextPage === undefined) {
      infiniteScroll.enable(false);
    } else {
      this.alertService.getNextAlerts(this.nextPage.href).subscribe(
        alertQueries => {
          this.alerts = this.alerts.concat(alertQueries._embedded.alerts.filter(alert => alert.alertStatus === 'OPEN'));
          this.nextPage = alertQueries._links.next;
          infiniteScroll.complete();
        });
    }
  }

}

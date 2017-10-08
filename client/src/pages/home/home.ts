import { Deletequery, Next } from './../../model/delete-query-response';
import { AddDeleteQueryPage } from './../add-delete-query/add-delete-query';
import { DeleteQueryService } from './../../providers/delete-query-service/delete-query-service';
import { Component } from '@angular/core';
import { NavController, InfiniteScroll } from 'ionic-angular';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  public deleteQueries: Deletequery[] = [];
  public nextPage: Next;

  constructor(public navCtrl: NavController, public deleteQueryService: DeleteQueryService) {

  }

  public ionViewWillEnter(): void {
    this.deleteQueryService.getDeleteQueries().subscribe(
      deleteQueries =>  {
        this.deleteQueries = deleteQueries._embedded.deleteQueries;
        this.nextPage = deleteQueries._links.next;
      });
  }

  public addDeleteQuery() {
    this.navCtrl.push(AddDeleteQueryPage);
  }

  public removeDeleteQuery(deleteQuery: Deletequery) {
    this.deleteQueryService.removeDeleteQuery(deleteQuery._links.self.href).subscribe(
      succ => this.deleteQueries = this.deleteQueries.filter(d => d !== deleteQuery),
    );
  }

  public infiniteEntries(infiniteScroll: InfiniteScroll): void {
    if (this.nextPage === undefined) {
      infiniteScroll.enable(false);
    } else {
      this.deleteQueryService.getNextDeleteQueries(this.nextPage.href).subscribe(
        deleteQueries => {
          this.deleteQueries = this.deleteQueries.concat(deleteQueries._embedded.deleteQueries);
          this.nextPage = deleteQueries._links.next;
          infiniteScroll.complete();
        });
    }
  }

}

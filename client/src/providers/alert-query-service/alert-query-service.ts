import { AlertQueryResponse, Alertquery } from './../../model/alert-query-response';

import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AlertQueryService {

  public readonly proxyPrefix: string = 'http://localhost:8080';

  constructor(public http: Http) { }

  public getAlertQueries(): Observable<AlertQueryResponse> {
    return this.http.get('/alertQueries?size=50').map(response => response.json());
  }

  public getNextAlertQueries(alertQueryPageURL: string): Observable<AlertQueryResponse> {
    return this.http.get(this.sanitizeURL(alertQueryPageURL)).map(response => response.json());
  }

  public addAlertQuery(alertQuery: Alertquery): Observable<Response> {
    const headers = new Headers({'Content-Type': 'application/json'});
    return this.http.post('/alertQueries', JSON.stringify(alertQuery), { headers: headers });
  }

  public removeAlertQuery(alertQueryURL: string): Observable<Response> {
    return this.http.delete(this.sanitizeURL(alertQueryURL));
  }

  public sanitizeURL(alertQueryURL: string): string {
    return alertQueryURL.replace(this.proxyPrefix, '');
  }
}

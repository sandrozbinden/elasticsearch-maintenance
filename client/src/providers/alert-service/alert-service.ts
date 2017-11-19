import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { AlertResponse, Alert } from '../../model/alert-response';

@Injectable()
export class AlertService {

  public readonly proxyPrefix: string = 'http://localhost:8080';

  constructor(public http: Http) { }

  public getAlerts(): Observable<AlertResponse> {
    return this.http.get('/alerts?size=50').map(response => response.json());
  }

  public getNextAlerts(alertPageURL: string): Observable<AlertResponse> {
    return this.http.get(this.sanitizeURL(alertPageURL)).map(response => response.json());
  }

  public completeAlert(alert: Alert): Observable<Response> {
    alert.alertStatus = 'CLOSED';
    alert.completedDate  = new Date();
    return this.http.put(this.sanitizeURL(alert._links.self.href), alert);
  }

  public sanitizeURL(alertQueryURL: string): string {
    return alertQueryURL.replace(this.proxyPrefix, '');
  }
}

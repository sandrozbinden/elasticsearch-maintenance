import { DeleteQueryResponse, Deletequery } from './../../model/delete-query-response';

import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DeleteQueryService {

  public readonly proxyPrefix: string = 'http://localhost:8080';

  constructor(public http: Http) { }

  public getDeleteQueries(): Observable<DeleteQueryResponse> {
    return this.http.get('/deleteQueries?size=50').map(response => response.json());
  }

  public getNextDeleteQueries(deleteQueryPageURL: string): Observable<DeleteQueryResponse> {
    return this.http.get(this.sanitizeURL(deleteQueryPageURL)).map(response => response.json());
  }

  public addDeleteQuery(deleteQuery: Deletequery): Observable<Response> {
    const headers = new Headers({'Content-Type': 'application/json'});
    return this.http.post('/deleteQueries', JSON.stringify(deleteQuery), { headers: headers });
  }

  public removeDeleteQuery(deleteQueryURL: string): Observable<Response> {
    return this.http.delete(this.sanitizeURL(deleteQueryURL));
  }

  public sanitizeURL(deleteQueryURL: string): string {
    return deleteQueryURL.replace(this.proxyPrefix, '');
  }
}

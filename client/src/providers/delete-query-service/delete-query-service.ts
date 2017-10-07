import { DeleteQueryResponse } from './../../model/delete-queries';

import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DeleteQueryService {

  constructor(public http: Http) { }

  public getDeleteQueries(): Observable<DeleteQueryResponse> {
    return this.http.get('/deletequery').map(response => response.json());
  }

  public addDeleteQuery(deleteQuery: DeleteQuery): Observable<Response> {
    const headers = new Headers({'Content-Type': 'application/json'});
    return this.http.post('/deletequery', JSON.stringify(deleteQuery), { headers: headers });
  }

  public removeDeleteQuery(deleteQuery: DeleteQuery): Observable<Response> {
    const headers = new Headers({'Content-Type': 'application/json'});
    return this.http.delete('/deletequery/' + deleteQuery.id);
  }
}

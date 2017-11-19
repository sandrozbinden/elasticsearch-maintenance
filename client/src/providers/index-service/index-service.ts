import { IndexField } from './../../model/index-field-response';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class IndexService {


  constructor(public http: Http) { }

  public getIndexFieldNames(): Observable<IndexField[]> {
    return this.http.get('/index/fields').map(response => response.json());
  }
}

import { AlertQueryPage } from './../alert-query/alert-query';
import { Component } from '@angular/core';

import { AlertPage } from './../alert/alert';
import { DeleteQueryPage } from '../delete-query/delete-query';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = AlertPage;
  tab2Root = DeleteQueryPage;
  tab3Root = AlertQueryPage;

  constructor() {

  }
}

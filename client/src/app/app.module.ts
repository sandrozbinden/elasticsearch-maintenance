import { AlertQueryService } from './../providers/alert-query-service/alert-query-service';
import { AlertPage } from './../pages/alert/alert';
import { IndexService } from './../providers/index-service/index-service';
import { AddDeleteQueryPage } from './../pages/add-delete-query/add-delete-query';
import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { DeleteQueryService } from '../providers/delete-query-service/delete-query-service';
import { HttpModule } from '@angular/http';
import { IndexPage } from '../pages/index/index';
import { AddAlertQueryPage } from '../pages/add-alert-query/add-alert-query';

@NgModule({
  declarations: [
    MyApp,
    IndexPage,
    ContactPage,
    HomePage,
    TabsPage,
    AlertPage,
    AddAlertQueryPage,
    AddDeleteQueryPage,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    IndexPage,
    ContactPage,
    HomePage,
    TabsPage,
    AlertPage,
    AddAlertQueryPage,
    AddDeleteQueryPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    DeleteQueryService,
    AlertQueryService,
    IndexService
  ]
})
export class AppModule {}

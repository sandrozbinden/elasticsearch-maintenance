import { DeleteQueryPage } from './../pages/delete-query/delete-query';
import { AlertService } from './../providers/alert-service/alert-service';
import { AlertQueryService } from './../providers/alert-query-service/alert-query-service';
import { AlertPage } from './../pages/alert/alert';
import { IndexService } from './../providers/index-service/index-service';
import { AddDeleteQueryPage } from './../pages/add-delete-query/add-delete-query';
import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { TabsPage } from '../pages/tabs/tabs';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { DeleteQueryService } from '../providers/delete-query-service/delete-query-service';
import { HttpModule } from '@angular/http';
import { AddAlertQueryPage } from '../pages/add-alert-query/add-alert-query';
import { AlertQueryPage } from '../pages/alert-query/alert-query';
import { PipesModule } from '../pipes/pipes.module';

@NgModule({
  declarations: [
    MyApp,
    TabsPage,
    AlertPage,
    AlertQueryPage,
    DeleteQueryPage,    
    AddAlertQueryPage,
    AddDeleteQueryPage,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    PipesModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    TabsPage,
    AlertPage,
    AlertQueryPage,
    DeleteQueryPage,
    AddAlertQueryPage,
    AddDeleteQueryPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AlertService,
    DeleteQueryService,
    AlertQueryService,
    IndexService
  ]
})
export class AppModule {}

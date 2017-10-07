import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AddDeleteQueryPage } from './add-delete-query';

@NgModule({
  declarations: [
    AddDeleteQueryPage,
  ],
  imports: [
    IonicPageModule.forChild(AddDeleteQueryPage),
  ],
})
export class AddDeleteQueryPageModule {}

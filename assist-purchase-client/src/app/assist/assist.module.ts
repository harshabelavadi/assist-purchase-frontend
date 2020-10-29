import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AssistComponent } from './assist.component';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import { DashboardModule } from '../dashboard/dashboard.module';

@NgModule({
  declarations: [AssistComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatSelectModule,
    MatButtonModule,
    DashboardModule
  ],
  exports:[AssistComponent]
})
export class AssistModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { DashboardComponent } from './dashboard.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { LoginModule } from '../login/login.module';
import { CardViewComponent } from './card-view/card-view.component';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import { MultiLineChartComponent } from './visualization/multi-line-chart/multi-line-chart.component';
import { PurchasedViewComponent } from './product-details/purchased-view/purchased-view.component';


@NgModule({
  declarations: [DashboardComponent, ProductDetailsComponent, CardViewComponent, MultiLineChartComponent, PurchasedViewComponent],
  imports: [
    CommonModule,
    LoginModule,
    FormsModule,
    MatTableModule,
    MatButtonModule
  ],
  exports: [DashboardComponent, CardViewComponent]
})
export class DashboardModule { }

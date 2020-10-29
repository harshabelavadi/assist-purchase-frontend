import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { LoginComponent } from './login.component';
import { AddRemoveProductsComponent } from './add-remove-products/add-remove-products.component';
import {MatSelectModule} from '@angular/material/select';
import { RemoveConfirmationComponent } from './add-remove-products/remove-confirmation/remove-confirmation.component';
import { ResultsViewComponent } from './add-remove-products/results-view/results-view.component';

@NgModule({
  declarations: [LoginComponent, AddRemoveProductsComponent, RemoveConfirmationComponent, ResultsViewComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatSelectModule
  ],
  exports: [LoginComponent]
})
export class LoginModule { }

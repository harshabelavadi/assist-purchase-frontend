import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AssistComponent } from './assist/assist.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductDetailsComponent } from './dashboard/product-details/product-details.component';
import { PurchasedViewComponent } from './dashboard/product-details/purchased-view/purchased-view.component';
import { AddRemoveProductsComponent } from './login/add-remove-products/add-remove-products.component';
import { RemoveConfirmationComponent } from './login/add-remove-products/remove-confirmation/remove-confirmation.component';
import { ResultsViewComponent } from './login/add-remove-products/results-view/results-view.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'productdetails/:id', component:  ProductDetailsComponent},
  { path: 'login', component: LoginComponent}, 
  { path: 'add-remove-products', component: AddRemoveProductsComponent },
  { path: 'remove-confirmation/:id', component: RemoveConfirmationComponent},
  { path: 'purchased-view/:id', component: PurchasedViewComponent},
  { path: 'results-view/:id', component: ResultsViewComponent },
  { path: 'assist', component: AssistComponent},
  { path: '**', redirectTo: '/dashboard', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }

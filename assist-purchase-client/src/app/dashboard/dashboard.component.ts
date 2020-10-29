import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from '../app.service';
import { Product } from '../models/Product';
import { DashboardService } from '../service/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  productList: Product[];
  constructor(private route: ActivatedRoute,
    private router: Router, private dashboardService: DashboardService, private appService: AppService) { }

  ngOnInit(): void {
    this.loadProductData();
  }

  loginRedirect(path:string): void {
        this.router.navigate([path], { relativeTo: this.route });
    }

  loadProductData() {
      this.dashboardService.getProductList().subscribe((response: Product[]) => {
      this.appService.productList = response;
      this.appService.productList.sort((p1, p2) => p1.pname < p2.pname ? -1 : p1.pname > p2.pname ? 1 : 0);
      this.productList = this.appService.productList;
      });
    }
}

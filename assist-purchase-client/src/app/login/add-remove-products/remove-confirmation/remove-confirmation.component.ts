import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/Product';
import { DashboardService } from 'src/app/service/dashboard.service';
import * as imageSourceJson from "../../../configuration/imagesources.json";

@Component({
  selector: 'app-remove-confirmation',
  templateUrl: './remove-confirmation.component.html',
  styleUrls: ['./remove-confirmation.component.css']
})
export class RemoveConfirmationComponent implements OnInit {
  tableDatasource : Array<any> = new Array();
  routerSub: Subscription;
  productDetails: Product = new Product();
  productId:number;
  headers: any = {"pname": "Product Name", "touchscreen": "Touchscreen Feature", "size": "Screen Size (inches)", "category": "Category", "transportMonitor": "Transport Feature"};
  values: any = {"true": "Yes", "false": "No", "bedside": "Bedside", "wearable": "Wearable"};
  imagename:string = "";
  imagedisplayFlag:boolean = true;

  constructor(private dashboardService: DashboardService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.routerSub = this.route.paramMap.subscribe((response) => {
      this.productId = Number(response.get('id'));
      this.getImagePath();  
      this.dashboardService.getProductById(this.productId).subscribe((response: Product) => {
        this.productDetails = response;
        for (let element in this.productDetails) {
          let value = null;
          if (element == "size" || element == "pname")
            value = this.productDetails[element];
          else
            value = this.values[this.productDetails[element]];

          this.tableDatasource.push([this.headers[element], value]);
        }
      });
    });
  }

  delete(decision:number)
  {
    if(decision){
      this.dashboardService.deleteProduct(this.productId).subscribe();
      this.router.navigate(["../../results-view", 1], { relativeTo: this.route });
    }
    else
    this.router.navigate(["dashboard"], { relativeTo: this.route });
  }

  getImagePath() {
    let jsonString = JSON.stringify(imageSourceJson["default"]);
    let arrayObject = JSON.parse(jsonString);
    for(let element of arrayObject){
      if (element.pid == this.productId) {
        this.imagedisplayFlag = true;
        this.imagename = element.imgname;
        return;
      }
    }
    this.imagedisplayFlag = false;
  }

}

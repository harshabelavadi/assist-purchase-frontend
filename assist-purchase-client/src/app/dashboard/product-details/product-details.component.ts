import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/Product';
import { DashboardService } from 'src/app/service/dashboard.service';
import  * as productDescription from '../../configuration/productdescription.json';
import * as statisticsJson from "../../configuration/salesdata.json";
import * as imageSourceJson from "../../configuration/imagesources.json";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  routerSub: Subscription;
  productDetails : Product = new Product();
  tableDatasource : any[] = [];
  headers: any = {"pname": "Product Name", "touchscreen": "Touchscreen Feature", "size": "Screen Size (inches)", "category": "Category", "transportMonitor": "Transport Feature", "description": "Description"};
  values: any = {"true": "Yes", "false": "No", "intellivue": "Intellivue", "effica": "Effica", "goldway": "Goldway"};
  productId: number;
  statisticsdata: any[] = [];
  benchmarkdata: any[] = [];
  triggerChange: boolean;

  emailAddress:string = "";
  contactNumber:string = "";
  errorMsg:string = 'Please enter both email address and contact number';
  errorFlag:boolean = false;
  displayGraphFlag:boolean = true;
  imagename:string = "";
  imagedisplayFlag:boolean = true;

  constructor(private dashboardService: DashboardService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.routerSub = this.route.paramMap.subscribe((response) => {
      
      this.productId = Number(response.get('id'));   
      this.getImagePath();   
      this.getMonitorSaleStats(); 
      this.executeDashboardGetService();
    });
  }

  executeDashboardGetService(){
    this.dashboardService.getProductById(this.productId).subscribe((response: Product) => {
        this.productDetails = response;
        this.getProductDescription();
        this.populateProductData();
      });        
  }

  populateProductData()
  {
    for (let element in this.productDetails) {
      let value: string | number;
      if(element == "pid")
        continue;
      (element == "size" || element == "pname" || element == "description") ? value = this.productDetails[element] : value = this.values[this.productDetails[element]];
      this.tableDatasource.push([this.headers[element], value]);
    }  
  }

  getProductDescription(){
    let jsonString = JSON.stringify(productDescription["default"]);
    let arrayObject = JSON.parse(jsonString);
    for(let element of arrayObject){
      if (element.pid == this.productId) {
        this.productDetails.description = element.description;
        break;
      }
    }
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

  getMonitorSaleStats() {
    let jsonString = JSON.stringify(statisticsJson["default"]);
    let arrayObject = JSON.parse(jsonString);
    let milliseconds:number, meanStats = {};

    for(let element of arrayObject){
      if (element.pid == this.productId) {
        this.statisticsdata.push([new Date(element.Date).getTime(), element.salecount]);
      }

      milliseconds = new Date(element.Date).getTime();
      if (milliseconds in meanStats){
        meanStats[milliseconds][0] += element.salecount;
        meanStats[milliseconds][1] += 1;
      }
      else 
        meanStats[milliseconds] = [element.salecount, 0];
    }

    if (this.statisticsdata.length == 0) {
      this.displayGraphFlag = false;
      return;
  }

    for(let element in meanStats){
      this.benchmarkdata.push([Number(element), Number((meanStats[element][0]/meanStats[element][1]).toFixed(2))]);
    }
  }

  validate() {
    if (this.emailAddress == "" || this.contactNumber == "")
      this.errorFlag = true;
    else
    {
      this.errorFlag = false;
      this.router.navigate(["../../purchased-view", this.productId], { relativeTo: this.route });
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { element } from 'protractor';
import { AppService } from 'src/app/app.service';
import { Product } from 'src/app/models/Product';
import { DashboardService } from 'src/app/service/dashboard.service';

@Component({
  selector: 'app-add-remove-products',
  templateUrl: './add-remove-products.component.html',
  styleUrls: ['./add-remove-products.component.css']
})
export class AddRemoveProductsComponent implements OnInit {
  productname:string = "";
  touchscreen:string = "";
  monitorsize:string = "";
  category:string = "";
  transportmonitor:string = "";
  selectedproductname: string = "";
  productsnameslist: string[] = [];
  categories: string[] = [];

  addErrorMsg = "Mandatory: Enter all fields";
  addErrorFlag = false;

  deleteErrorMsg = "Select an option from dropdown to delete";
  deleteErrorFlag = false;

  constructor(private dashboardService: DashboardService, private appService: AppService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.getProductNames();
  }

  getProductNames() {
    if(this.appService.productList) {
      this.appService.productList.forEach(element => {
        let capitalize_category = element.category[0].toUpperCase()+element.category.slice(1)
        if(!this.categories.includes(capitalize_category))
          this.categories.push(capitalize_category);

        if (!this.productsnameslist.includes(element.pname))
          this.productsnameslist.push(element.pname);
      });
      this.productsnameslist.sort((num1, num2) => num1 < num2 ? -1 : num1 > num2? 1 : 0);
      this.categories.sort((category1, category2) => category1 < category2 ? -1 : category1 > category2? 1 : 0);
    }
  }

  validateAdd(){
    let properties = [this.productname, this.touchscreen, this.monitorsize, this.category, this.transportmonitor]
    for (let element of properties){
      if (element == ""){
        this.addErrorFlag = true;
        return;
      }
    }
     this.addProduct();
  }

  addProduct(){
    let newProduct = this.setProductProperties();
    this.addErrorFlag = false;
    this.dashboardService.addProduct(newProduct).subscribe();
    this.redirectToResultsView();
  }

  setProductProperties() {
    let newProduct : Product = new Product();
    newProduct.pname = this.productname;
    newProduct.touchscreen = this.touchscreen == "Yes" ? true : false;
    newProduct.transportMonitor = this.transportmonitor == "Yes" ? true : false;
    newProduct.size = Number(this.monitorsize);
    newProduct.category = this.category.toLowerCase();
    return newProduct;
  }

  validateDelete(){
    (this.selectedproductname == "")? this.deleteErrorFlag = true: this.deleteErrorFlag = false;
    this.deleteSelectedItem();
  }

  deleteSelectedItem() {
    for (let element of this.appService.productList){
      if (element.pname == this.selectedproductname)
        this.redirectToDeleteConfirmation(element.pid);
    }
  }

  redirectToDeleteConfirmation(pid:number) {
    this.router.navigate(["../remove-confirmation", pid], { relativeTo: this.route });
  }

  redirectToResultsView() {
    this.router.navigate(["../results-view", 0], { relativeTo: this.route });
  }
}

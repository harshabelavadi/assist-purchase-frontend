import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Product } from '../models/Product';
import { DashboardService } from '../service/dashboard.service';

@Component({
  selector: 'app-assist',
  templateUrl: './assist.component.html',
  styleUrls: ['./assist.component.css']
})
export class AssistComponent implements OnInit {
  size = null;
  touchscreen = '';
  category = '';
  transportMonitor = '';
  productList: Product[] = Object.assign([], this.appService.productList);
  unique_sizeList:number[] = [];
  results: Product[];
  categories: string[] = [];

  constructor(private appService: AppService, private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.getItemsForDropDown();
  }

  getItemsForDropDown() {
      this.appService.productList.forEach(element => {
        let capitalize_category = element.category[0].toUpperCase()+element.category.slice(1)
        if(!this.categories.includes(capitalize_category))
          this.categories.push(capitalize_category);

        if (!this.unique_sizeList.includes(element.size))
          this.unique_sizeList.push(element.size);
      
        });
      this.unique_sizeList.sort((num1, num2) => num1 < num2 ? -1 : num1 > num2? 1 : 0);
      this.categories.sort((category1, category2) => category1 < category2 ? -1 : category1 > category2? 1 : 0);
  }

  getMonitorList() {
    this.results = [];
    (this.areAllOptionsSelected())?this.setMonitorProperties() : this.refreshAllProperties();
  }

  setMonitorProperties(){
    let transportMonitor_boolean = false, touchscreen_boolean = false;
    transportMonitor_boolean = this.transportMonitor == 'Yes' ? true : false;
    touchscreen_boolean = this.touchscreen == 'Yes' ? true : false;
    this.category = this.category.toLowerCase();
    this.executeFilterService(transportMonitor_boolean, touchscreen_boolean);
  }

  executeFilterService(transportMonitor_boolean, touchscreen_boolean){
    this.dashboardService.getProductListByFilter(touchscreen_boolean, this.size, this.category, transportMonitor_boolean).subscribe((response: Product[]) => {
      this.results = response;
      this.results.sort((p1, p2) => p1.pname < p2.pname ? -1 : p1.pname > p2.pname ? 1 : 0);
    });
  }

  refreshAllProperties(){
    this.size = 0;
    this.touchscreen = '';
    this.category = '';
    this.transportMonitor = '';
  }

  areAllOptionsSelected()
  {
    let properties = [this.touchscreen, this.size, this.category, this.transportMonitor]
    for (let element of properties){
      if (element == ""){
        return true;
      }
    }
    return false;
  }

}

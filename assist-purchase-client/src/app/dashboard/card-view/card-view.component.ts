import { Component, Input, OnChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/Product';
import * as imageSourceJson from "../../configuration/imagesources.json";

@Component({
  selector: 'app-card-view',
  templateUrl: './card-view.component.html',
  styleUrls: ['./card-view.component.css']
})
export class CardViewComponent implements OnChanges {
  @Input() productList: Product[];
  imagename = {};

  constructor(private route: ActivatedRoute,
    private router: Router) { }

  ngOnChanges(): void {
    this.storeImagePaths();
  }

  detailsRedirect(path:string, id:number): void {
    this.router.navigate([path, id], { relativeTo: this.route });
    }

  storeImagePaths() {
    let jsonString = JSON.stringify(imageSourceJson["default"]);
    let arrayObject = JSON.parse(jsonString);
    for(let element of arrayObject){
      this.imagename[element.pid] = element.imgname;
    }
  }

  isImageAvailable(pid:number){
    if(pid in this.imagename)
      return true;
    return false;
  }
}

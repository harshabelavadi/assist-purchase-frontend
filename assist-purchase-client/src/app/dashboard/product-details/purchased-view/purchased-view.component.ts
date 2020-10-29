import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import * as philipsContact from "../../../configuration/philipscontactpersonnel.json";

@Component({
  selector: 'app-purchased-view',
  templateUrl: './purchased-view.component.html',
  styleUrls: ['./purchased-view.component.css']
})
export class PurchasedViewComponent implements OnInit {
  message: string = "";
  feedbackMessage: string = "";
  routerSub: Subscription;
  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.routerSub = this.route.paramMap.subscribe((response) => {
      const pid = Number(response.get('id'));
      let jsonString = JSON.stringify(philipsContact["default"]);
      let arrayObject = JSON.parse(jsonString);

      for(let element of arrayObject){
        if (element.pid == pid) {
          this.message = "Your order for purchase of item with Product-id:"+pid.toString()+" is approved. You will be contacted by email or mobile by Philips personnel. Name: "+
          element.name+", Email: "+element.email+", Mobile: "+element.mobile;
          break;
        }
      }
    });
  }

  redirect() {
    this.router.navigate(["dashboard"], { relativeTo: this.route });
  }
}

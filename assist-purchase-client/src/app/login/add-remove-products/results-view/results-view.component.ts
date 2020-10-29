import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-results-view',
  templateUrl: './results-view.component.html',
  styleUrls: ['./results-view.component.css']
})
export class ResultsViewComponent implements OnInit {
  message: string;
  routerSub: Subscription;
  constructor(private route: ActivatedRoute,
    private router: Router) { }

  
  ngOnInit(): void {
    this.routerSub = this.route.paramMap.subscribe((response) => {
      const flag = Number(response.get('id'));
      if(flag)
      this.message = "The Product has been removed from Philips purchase portal successfully!";
      else
      this.message = "The Product has been added to Philips purchase portal successfully!";
  });
}

}

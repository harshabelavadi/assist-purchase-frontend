import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Patient Monitor Products';

  constructor(private route: ActivatedRoute,
    private router: Router) { }

    redirect(path:string): void {
    this.router.navigate([path], { relativeTo: this.route });
    }
}

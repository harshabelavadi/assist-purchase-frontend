import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { element } from 'protractor';
import { User } from '../models/user';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = "";
  password = "";
  errorMsg = 'Invalid Credentials';
  errorFlag = false;
  usersList : User[] = [];

  constructor(private route: ActivatedRoute,
    private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  redirect(path:string): void {
    this.router.navigate([path], { relativeTo: this.route });
    }

    validate() {

      this.usersList = this.loginService.getLoginInformation();      
      for(let element of this.usersList){
         this.authenticate(element.uname, element.password)?this.redirect("../add-remove-products"):this.errorFlag = true;
        }          
    }

    authenticate(username:string, password:string) {
      return (this.username == username && this.password == password);
    }

}

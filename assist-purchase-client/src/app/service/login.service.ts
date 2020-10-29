import { Injectable } from '@angular/core';
import  * as loginData from '../configuration/loginconfig.json';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  userDetails : User[];
  constructor() { }

  getLoginInformation()  {
    let jsonString = JSON.stringify(loginData["default"]);
    let arrayObject = JSON.parse(jsonString);
    this.userDetails = []
    for(let element of arrayObject){
        let user: User = new User();
        user.uid = element.uid;
        user.uname = element.uname;
        user.password = element.password;
        user.phone = element.phone;
        user.email = element.email;
        this.userDetails.push(user);
    }
    return this.userDetails;
    }
}

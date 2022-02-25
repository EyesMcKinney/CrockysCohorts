import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from '../user';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {
  user: User | undefined;
  adminUser: Boolean | undefined; 
  // username: string |undefined ;
  // private user = new Subject<string>();

  constructor(private loginService: LoginService) {
    this.adminUser = false ;
  }


  ngOnInit(): void {}

  checkUser(input: string): void {
    input = input.trim();
    if(!input) { return; }


    if( input == "admin"){
      this.adminUser = true ; 
      
    }else{
      this.adminUser = false ;
      // this.loginService.getUser(input)
      //   .subscribe();
    }
    
  }


}

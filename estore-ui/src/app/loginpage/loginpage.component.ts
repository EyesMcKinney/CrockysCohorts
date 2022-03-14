import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from '../user';
import { LoginService } from '../login.service';
import { Location } from '@angular/common';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';
import { AdminComponent } from '../admin/admin.component';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {
  user?: User | undefined;
  adminUser: Boolean | undefined; 
  // username: string |undefined ;
  // private user = new Subject<string>();

  constructor(private loginService: LoginService, private location: Location, 
    private appComponent: AppComponent, private router: Router) {
    this.adminUser = false ;
    // initialize user to blank user(maybe? might not be necessary): this.user = new User(""); // user w/ no name
  }


  ngOnInit(): void { }

  /**
   * Load previous page.
   */
  goBack(): void {
      if (this.user?.username == "admin") { 
          return;
      } else {
        this.location.back();
      }
  }

  /**
   * Login to the {@link User User} from storage with the provided username
   * 
   * @param username name of the desired {@link User User} to login to
   */
  login(username: string): void {
      this.appComponent.changeAdminUser ; 
      username = username.trim();
      if (!username) {return;}
      if(username == "admin"){
        this.appComponent.changeAdminUser() ;
        this.router.navigate(['admin']);
      }else{
        this.loginService.getUser(
            username
        ).subscribe(user => this.user = user);
      }
      //this.checkUser();
      
      
      //this.goBack();
  }

  /**
   * Create a new {@link User User} with provided username
   * 
   * @param username name of the desired {@link User User} to create
   */
  createUser(username: string): void {
      username = username.trim();
      if (!username) {return;}
      if (username == "admin") {
          //TODO use message service to print error message
      }

      this.loginService.createUser( { username } as User)
      .subscribe(user => {
          this.user = user;
      });
      this.goBack();
  }



}

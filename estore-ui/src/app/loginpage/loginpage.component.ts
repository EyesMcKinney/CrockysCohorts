import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from '../user';
import { LoginService } from '../login.service';
import { Location } from '@angular/common';
import { AppRoutingModule } from '../app-routing.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {
  user?: User;
  adminUser: Boolean | undefined; 

  constructor(private loginService: LoginService, private location: Location, private router: Router) {
    this.adminUser = false ;
    // initialize user to blank user(maybe? might not be necessary): this.user = new User(""); // user w/ no name
  }


  ngOnInit(): void { }

  /**
   * Update the current user in this {@link LoginpageComponent LoginpageComponent} and in {@link LoginService LoginService}.
   * @param newUser {@link User User} that's signed in
   */
  setUser(newUser: User): void {
      this.user = newUser;
      this.loginService.userLogin(newUser);
  }

  /**
   * Load previous page.
   */
  goBack(): void {
      if (this.user?.username == "admin") { 
          return;  // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ROUTE TO ADMIN COMPONENT
      } else {
        //this.router.navigate(); navigate method doesn't exist??? this shouldn't be a problem... <<<<<<<<<<<<<<<<
        this.location.back();
      }
  }

  /**
   * Login to the {@link User User} from storage with the provided username
   * 
   * @param username name of the desired {@link User User} to login to
   */
  login(username: string): void {
      username = username.trim();
      if (!username) {return;}

      this.loginService.getUser(
            username
        ).subscribe(user => {
            this.setUser(user);
            this.checkUser();
            this.goBack();
        });
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
      .subscribe(user => this.setUser(user));
      this.goBack();
  }

  /**
   * Determins if {@link User User} that's signed in is an admin or not
   */
  checkUser(): void {
    if(!this.user?.username) { return; }

    if( this.user.username == "admin"){
      this.adminUser = true ; 
      this.router.navigate(['admin']);
      
      
    }else{
      this.adminUser = false ;
      // this.loginService.getUser(input)
      //   .subscribe();
    }
  }

}

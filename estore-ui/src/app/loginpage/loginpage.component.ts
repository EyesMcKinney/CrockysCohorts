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
  user?: User | undefined;
  adminUser: Boolean | undefined; 
  // username: string |undefined ;
  // private user = new Subject<string>();

  constructor(private loginService: LoginService) {
    this.adminUser = false ;
    // initialize user to blank user(maybe? might not be necessary): this.user = new User(""); // user w/ no name
  }


  ngOnInit(): void { }

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
        ).subscribe(user => this.user = user);
      this.checkUser();
  }

  /**
   * Create a new {@link User User} with provided username
   * 
   * @param username name of the desired {@link User User} to create
   */
  createUser(username: string): void {
      username = username.trim();
      if (!username) {return;}

      this.loginService.createUser( { username } as User)
      .subscribe(user => {
          this.user = user;
          this.goBack();
      })
  }

  /**
   * Determins if {@link User User} that's signed in is an admin or not
   */
  checkUser(): void {
    if(!this.user?.username) { return; }


    if( this.user.username == "admin"){
      this.adminUser = true ; 
      
    }else{
      this.adminUser = false ;
      // this.loginService.getUser(input)
      //   .subscribe();
    }
  }


}

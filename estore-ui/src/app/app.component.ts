import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './user';
import { LoginService } from './login.service';
import { Subscription } from 'rxjs';
import { MessageService } from './message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Crocy\'s Crochet';
  adminUser: Boolean | undefined ;
  otherUser: Boolean | undefined ;

  constructor( private router: Router ) {
    this.adminUser = false ;
    this.otherUser = false ;
  }

  changeAdminUser(): void {
    this.adminUser = !this.adminUser ; 
  }

  changeOtherUser(): void {
    this.otherUser = !this.otherUser ; 
  }

  logOut(): void {
    this.adminUser = false ; 
    this.otherUser = false ;
    this.router.navigate(['homepage']);
  }

  
  /**
   * Current {@link User User} logged in.
   */
  user: User;

  /**
   * {@link Subscription Subscription} to subscribe to user changes.
   */
  subscription!: Subscription;

  constructor(private loginService: LoginService, private message: MessageService) {
      this.user = {id:-1, username:"dummy user"} as User;
      this.subscription = this.loginService.getLoggedInUser().subscribe(user => this.user = user);
  }

  public ngOnInit(): void {  // subscribe to user login
      this.subscription = this.loginService.getLoggedInUser()
          .subscribe(user => this.updateUser(user));
  }

  updateUser(newUser: User): void {
      this.user = newUser;
      this.message.add("@app component: user: " + this.user.username + " logged in")
  }
}



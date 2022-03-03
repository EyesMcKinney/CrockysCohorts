import { Component } from '@angular/core';
import { User } from './user';
import { LoginService } from './login.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Crocy\'s Crochet';
  user?: User = undefined;
  subscription: Subscription | undefined;

  constructor(private loginService: LoginService) {}

  public ngOnInit(): void {  // subscribe to user login
      this.subscription = this.loginService.getLoggedInUser()
          .subscribe(user => this.user = user);
  }

  updateUser(newUser: User): void {
      this.user = newUser;
  }
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';

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


}



import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Crocy\'s Crochet';
  adminUser: Boolean | undefined ;
  otherUser: Boolean | undefined ;

  constructor() {
    this.adminUser = false ;
    this.otherUser = false ;
  }

  changeAdminUser(): void {
    this.adminUser = !this.adminUser ; 
  }

  changeOtherUser(): void {
    this.otherUser = !this.otherUser ; 
  }

}



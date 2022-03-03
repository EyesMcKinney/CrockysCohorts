import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  // TODO: get input from root: https://angular.io/guide/inputs-outputs#watching-for-input-changes
  // TODO: update changes to user(?): https://angular.io/guide/lifecycle-hooks#onchanges

}

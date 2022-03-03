import { Component, OnInit, Input } from '@angular/core';
import { User } from '../user';


/**
 * Page header, with respect to current {@link User User} logged in.
 * 
 * @author Stevie Alvarez
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

    constructor() { }

    @Input() currentUser?: User;

    ngOnInit(): void {
    }

    // TODO: get input from root: https://angular.io/guide/inputs-outputs#watching-for-input-changes
    // TODO: update changes to user(?): https://angular.io/guide/lifecycle-hooks#onchanges

}

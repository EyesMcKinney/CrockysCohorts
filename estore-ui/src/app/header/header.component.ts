import { Component, OnInit, Input, SimpleChange } from '@angular/core';
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

    admin:boolean | undefined;

    @Input() currentUser: User;

    constructor() {
        this.currentUser = {id:-1, username:"dummy user"} as User;
    }

    ngOnInit(): void {
    }

    /**
     * Update user on change
     * @param changes 
     */
    ngOnChanges(changes: SimpleChange) {
        //if (changes.currentValue) { // check if user logged in is admin 

        //} else {  // otherwise, its a user

        //}

    }

    // TODO: get input from root: https://angular.io/guide/inputs-outputs#watching-for-input-changes
    // TODO: update changes to user(?): https://angular.io/guide/lifecycle-hooks#onchanges

}

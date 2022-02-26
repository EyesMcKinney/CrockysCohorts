import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { User } from './user';


/**
 * User login HTTP Request mapings.
 * 
 * @author Tylin Hartman
 * @author Stevie Alvarez
 */
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = 'http://localhost:8080/login';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  constructor(private http: HttpClient) { }

  getUser(username: string): Observable<User> {
    const url = `${this.loginUrl}/?name=${"name"}`;
    return this.http.get<User[]>(url)
      .pipe(
        map(users => users[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          //this.log(`${outcome} hero id=${id}`);
        }),
        //catchError(this.handleError<Hero>(`getHero id=${id}`))
      );
  }


  createUser(user: User): Observable<User> { return new Observable; }

}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { User } from './user';


/**
 * {@linkplain User User} login HTTP Request mapings.
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

  /**
     * GET a {@link User User} from the server.
     * 
     * @param username The username of the desired {@link User User}.
     * @returns The desired {@link User User} from storage
   */
  getUser(username: string): Observable<User> {
    const url = `${this.loginUrl}/${username}`;
    return this.http.get<User>(url);
  }

  /**
   * 
   * @param user 
   * @returns 
   */
  createUser(user: User): Observable<User> { return new Observable; }

}

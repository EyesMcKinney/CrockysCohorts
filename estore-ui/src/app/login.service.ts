import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap, Subject } from 'rxjs';
import { User } from './user';
import { MessageService } from './message.service';


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

  private userUpdate = new Subject<User>();
  private loginUrl = 'http://localhost:8080/login';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  constructor(private http: HttpClient, private message: MessageService) { }

  /**
   * Access the current logged in {@link User User}.
   * @returns the {@link User User} that's currently logged in
   */
  public getLoggedInUser(): Observable<User> {
      return this.userUpdate.asObservable();
  }

  /**
   * Update the current {@link User User} that's logged in.
   * 
   * @param newUser the {@link User User} that's logged in
   */
  public userLogin(newUser: User): void {
      this.userUpdate.next(newUser);
  }

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
   * POST (create/add) a {@link User User} to the server.
   * 
   * @param user The {@link User User} to create
   * @returns The new {@link User User} that's been made
   */
  createUser(user: User): Observable<User> { 
      return this.http.post<User>(this.loginUrl, user, this.httpOptions);
  }
}

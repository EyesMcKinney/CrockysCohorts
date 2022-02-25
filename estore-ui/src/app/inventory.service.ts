import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Product } from './product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import {catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

    private productsUrl = 'http://localhost:8080/products';  // URL to web api

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }


    //private handleError<T>(operation = 'operation', result?: T) { return new Observable ; }


    /**
     * 
     * @returns 
     */
    getInventory(): Observable<Product[]> { 
      return this.http.get<Product[]>(this.productsUrl)
      .pipe(
        //tap(_ => this.log('fetched heroes')),
       // catchError(this.handleError<Product[]>('getInventory', []))
      );
    }


    /**
     * 
     * @param id 
     * @returns 
     */
    getProduct(id: number): Observable<Product> { 
      const url = `${this.productsUrl}/?id=${id}`;
    return this.http.get<Product[]>(url)
      .pipe(
        map(products => products[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          //this.log(`${outcome} hero id=${id}`);
        }),
        //catchError(this.handleError<Hero>(`getHero id=${id}`))
      );
     }


    getUser(name: string): Observable<User>{return new Observable;}


    /**
     * 
     * @param product 
     * @returns 
     */
    updateProduct(product: Product): Observable<any> { 
      return this.http.put(this.productsUrl, product, this.httpOptions).pipe(
        //tap(_ => this.log(`updated hero id=${hero.id}`)),
        //catchError(this.handleError<any>('updateHero'))
      );
     }


    /**
     * 
     * @param product 
     * @returns 
     */
    addProduct(product: Product): Observable<Product> { 
      return this.http.post<Product>(this.productsUrl, product, this.httpOptions).pipe(
        //tap((newHero: Hero) => this.log(`added hero w/ id=${newHero.id}`)),
        //catchError(this.handleError<Hero>('addHero'))
      );
     }

    /**
     * 
     * @param id 
     * @returns 
     */
    deleteProduct(id: number): Observable<Product> { 
      const url = `${this.productsUrl}/${id}`;

      return this.http.delete<Product>(url, this.httpOptions).pipe(
        //tap(_ => this.log(`deleted hero id=${id}`)),
        //catchError(this.handleError<Hero>('deleteHero'))
      );
     }


    /**
     * 
     * @param prompt 
     * @returns 
     */
    searchProducts(prompt: string): Observable<Product[]> { 
      if (!prompt.trim()) {
        // if not search term, return empty hero array.
        return of([]);
      }
      return this.http.get<Product[]>(`${this.productsUrl}/?name=${prompt}`).pipe(
        //tap(x => x.length ?
          // this.log(`found heroes matching "${term}"`) :
           //this.log(`no heroes matching "${term}"`)),
       // catchError(this.handleError<Hero[]>('searchHeroes', []))
      );
     }
}

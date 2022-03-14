/**
 * HTTP Request mapings.
 * 
 * @author Holden Lalumiere, Tylin Hartman, Alex Vernes, Isaac McKinney, Stevie Alvarez
 */

import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Product } from './product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';
//import {catchError, map, tap } from 'rxjs/operators';

/**
 * Handles HTTP Requests via respective methods.
 */
@Injectable({
  providedIn: 'root'
})
export class InventoryService {

    private productsUrl = 'http://localhost:8080/products';  // URL to web api

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(
        private http: HttpClient,
        private messageService: MessageService) { }


    //private handleError<T>(operation = 'operation', result?: T) { }


    /**
     * 
     * @returns 
     */
    getInventory(): Observable<Product[]> { 
        return this.http.get<Product[]>(this.productsUrl);
    }


    /**
     * GET a {@link Product} from the server.
     * 
     * @author Stevie Alvarez
     * 
     * @param id The ID of the desired product.
     * @returns The desired product from storage.
     */
    getProduct(id: number): Observable<Product> { 
        const url = `${this.productsUrl}/${id}`;
        return this.http.get<Product>(url);
    }


    /**
     * Update a {@link Product} product on the server
     * 
     * @param product the product to update
     * @returns the updated Product
     * @author Holden Lalumiere
     */
    updateProduct(product: Product): Observable<any> {
        return this.http.put(this.productsUrl, product, this.httpOptions);
    }


    /**
     * 
     * @param product 
     * @returns 
     */
    addProduct(product: Product): Observable<Product> { return new Observable; }

    /**
     * Delete a {@link Product} from the server
     * 
     * @param id the ID of the product
     * @returns the product deleted
     * @author Alex Vernes
     */
    deleteProduct(id: number): Observable<Product> { 
        const url = `${this.productsUrl}/${id}`;
        return this.http.delete<Product>(url, this.httpOptions);
    }


    /**
     * 
     * @param prompt 
     * @returns 
     */
    searchProducts(text: string): Observable<Product[]> { 
        if (!text.trim()) {
            return of([]);
        }
        return this.http.get<Product[]>(`${this.productsUrl}/?name=${text}`).pipe(
            tap(x => x.length ?
               this.log(`found products matching "${text}"`) :
               this.log(`no products matching "${text}"`)),
            catchError(this.handleError<Product[]>('searchProducts', []))
        );
    }

    private log(message: string) {
        this.messageService.add(`InventoryService: ${message}`);

    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
    
          // TODO: send the error to remote logging infrastructure
          console.error(error); // log to console instead
    
          // TODO: better job of transforming error for user consumption
          this.log(`${operation} failed: ${error.message}`);
    
          // Let the app keep running by returning an empty result.
          return of(result as T);
        };
      }
}

/**
 * HTTP Request mapings.
 * 
 * @author Holden Lalumiere, Tylin Hartman, Alex Vernes, Isaac McKinney, Stevie Alvarez
 */

import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Product } from './product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

    constructor(private http: HttpClient) { }


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
     * Adds a new {@link Product} to server 
     * 
     * @param product 
     * @returns the product to be added
     * @author Tylin Hartman
     */
    addProduct(product: Product): Observable<Product> { 
        return this.http.post<Product>(this.productsUrl, product, this.httpOptions); 
    }

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
    searchProducts(prompt: string): Observable<Product[]> { 
        if (!prompt.trim()) {
            return of([]);
        }
        return this.http.get<Product[]>(`${this.productsUrl}/?name=${prompt}`);
    }
}

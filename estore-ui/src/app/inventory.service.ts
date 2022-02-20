import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { Product } from './product';

import { HttpClient, HttpHeaders } from '@angular/common/http';
//import {catchError, map, tap } from 'rxjs/operators';

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
    getInventory(): Observable<Product[]> { return new Observable; }


    /**
     * 
     * @param id 
     * @returns 
     */
    getProduct(id: number): Observable<Product> { return new Observable; }


    /**
     * 
     * @param product 
     * @returns 
     */
    updateProduct(product: Product): Observable<any> { return new Observable; }


    /**
     * 
     * @param product 
     * @returns 
     */
    addProduct(product: Product): Observable<Product> { return new Observable; }

    /**
     * 
     * @param id 
     * @returns 
     */
    deleteProduct(id: number): Observable<Product> { return new Observable; }


    /**
     * 
     * @param prompt 
     * @returns 
     */
    searchProducts(prompt: string): Observable<Product[]> { return new Observable; }
}

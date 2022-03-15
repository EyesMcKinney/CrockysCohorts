/**
 * Manages searching for a product
 * 
 * @author Tylin Hartman
 */

import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import { Product } from '../product';
import { InventoryService } from '../inventory.service';

/**
 * Provides methods to find and return 
 * relevant {@linkplain Product}s
 * 
 * @author Tylin Hartman
 */
@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {

  /** {@link Product}s of interest. */
  products$!: Observable<Product[]>;

  /** User input search term */
  private searchTerms = new Subject<string>();  

  constructor(private inventoryService: InventoryService) { }

  /**
   * Updates searchterms as user provides new input
   * 
   * @param term User input string
   */
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {

    this.products$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.inventoryService.searchProducts(term)),
    );
  }

}

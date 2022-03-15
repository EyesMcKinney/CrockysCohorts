/**
 * Manages the website's homepage
 * 
 * @author Tylin Hartman
 */

import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { InventoryService } from '../inventory.service';
import { AppComponent } from '../app.component';
import { MessageService } from '../message.service';


/**
 * Provides methods to handle inventory
 * 
 * @author Tylin Hartman
 */
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: [ './homepage.component.css' ]
})
export class HomepageComponent implements OnInit {

  products: Product[] = [];  // An array of products

  constructor(private inventoryService: InventoryService, private appcomp: AppComponent, private message: MessageService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  /**
   * Get an array of products from the server using 
   * inventory Service
   */
  getProducts(): void {
    this.inventoryService.getInventory()
      .subscribe(products => {
        this.products = products;
        if (this.products.length == 0){
          this.message.add("No Products Availible")
        }
      });
  }

}
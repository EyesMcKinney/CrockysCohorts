/**
 * Manages the admin page
 * 
 * @author Tylin Hartman
 */

import { Component, OnInit } from '@angular/core';
import { InventoryService } from '../inventory.service';
import { Product } from '../product';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  /** Array of products */
  products: Product[] = [];

  constructor(private inventoryService: InventoryService) { }

  ngOnInit(): void {
    this.getProducts();
  }
  
  /**
   * Retrieves inventory to be displayed
   */
  getProducts(): void {
    this.inventoryService.getInventory()
      .subscribe(products => this.products = products);
  }

}

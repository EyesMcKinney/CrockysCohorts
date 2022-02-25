import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: [ './homepage.component.css' ]
})
export class DashboardComponent implements OnInit {
  products: Product[] = [];

  constructor(private inventoryService: InventoryService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.inventoryService.getInventory()
      .subscribe(products => this.products = this.products.slice(1, 5));
  }
}
import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: [ './homepage.component.css' ]
})
export class HomepageComponent implements OnInit {
  products: Product[] = [];

  constructor(private inventoryService: InventoryService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.inventoryService.getInventory()
      .subscribe(products => this.products = products.slice(1, 5));
  }
}
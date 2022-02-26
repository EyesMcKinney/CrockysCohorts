import { Component, OnInit } from '@angular/core';
import { InventoryService } from '../inventory.service';
import { Product } from '../product';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  products: Product[] = [];

  constructor(private inventoryService: InventoryService) { }

  ngOnInit(): void {
    this.getProducts();
  }
  
  getProducts(): void {
    this.inventoryService.getInventory()
      .subscribe(products => this.products = products);
  }

}

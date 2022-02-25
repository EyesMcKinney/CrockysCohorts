import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styleUrls: ['./product-info.component.css']
})
export class ProductInfoComponent implements OnInit {

  products: Product[] = [] ;

  constructor(private inventoryService: InventoryService) { }

  ngOnInit(): void {
    this.getProducts() ;
  }

  getProducts(): void {
    this.inventoryService.getInventory()
    .subscribe(products => this.products = products);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.inventoryService.addProduct({ name } as Product)
      .subscribe(product => {
        this.products.push(product);
      });
  }

  delete(product: Product): void {
    this.products = this.products.filter(h => h !== product);
    this.inventoryService.deleteProduct(product.id).subscribe();
  }
}

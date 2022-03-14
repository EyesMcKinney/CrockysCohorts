import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { InventoryService } from '../inventory.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: [ './homepage.component.css' ]
})
export class HomepageComponent implements OnInit {
  products: Product[] = [];

  constructor(private inventoryService: InventoryService, private appcomp: AppComponent) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.inventoryService.getInventory()
      .subscribe(products => this.products = products);
  }
  
  tester(): void{
    this.appcomp.adminUser = !this.appcomp.adminUser ;
  }
}
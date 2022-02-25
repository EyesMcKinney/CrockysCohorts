import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Product } from '../product';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styleUrls: ['./product-info.component.css']
})
export class ProductInfoComponent implements OnInit {
  product: Product | undefined ;

  constructor(
    private route: ActivatedRoute,
    private inventoryService: InventoryService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getProduct() ;
  }

  getProduct(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.inventoryService.getProduct(id)
      .subscribe(product => this.product = product);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.product) {
      this.inventoryService.updateProduct(this.product)
        .subscribe(() => this.goBack());
    }
  }
}

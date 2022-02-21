/**
 * Manages product information.
 * 
 * @author Stevie Alvarez
 */


import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Product } from '../product';
import { InventoryService } from '../inventory.service';


/**
 * Provides methods to interact with individual products.
 * 
 * @author Stevie Alvarez
 */
@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styleUrls: ['./product-info.component.css']
})
export class ProductInfoComponent implements OnInit {

    constructor(
        private route: ActivatedRoute, 
        private inventoryService: InventoryService,
        private location: Location
    ) { }

    /**
     * Product of interest.
     */
    @Input() product?: Product;


    ngOnInit(): void {
        this.getProduct();
    }


    /**
     * GET a product from storage.
     */
    getProduct(): void {
        this.inventoryService.getProduct(
            Number(this.route.snapshot.paramMap.get("id"))  // id routed to
        ).subscribe(product => this.product = product);
    }


    /**
     * 
     */
    goBack(): void {}


    /**
     * Update the product information in storage.
     */
    save(): void {
        if (this.product) {
            this.inventoryService.updateProduct(this.product)
                .subscribe(() => this.goBack());
        }
    }
}

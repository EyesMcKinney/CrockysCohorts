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
 * Provides methods to interact with individual {@linkplain Product}.
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
     * {@link Product} of interest.
     */
    @Input() product?: Product;


    ngOnInit(): void {
        this.getProduct();
    }


    /**
     * GET a {@link product Product} from storage.
     */
    getProduct(): void {
        this.inventoryService.getProduct(
            Number(this.route.snapshot.paramMap.get("id"))  // id routed to
        ).subscribe(product => this.product = product);
    }


    /**
     * Load parent location/page.
     */
    goBack(): void {
        this.location.back();
    }


    /**
     * Update the {@link product Product} information in storage.
     */
    save(): void {
        if (this.product) {
            this.inventoryService.updateProduct(this.product)
                .subscribe(() => this.goBack());
        }
    }
}

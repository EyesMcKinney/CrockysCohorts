
/**
 * Manages product information.
 * 
 * @author Stevie Alvarez
 * @author Tylin Hartman
 */

 import { Component, OnInit, Input } from '@angular/core';
 import { ActivatedRoute } from '@angular/router';
 import { Location } from '@angular/common';
 
 import { Product } from '../product';
 import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

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


  /**
   * Delete the {@link product Product} from storage. 
   * 
   * @author Alex Vernes
   */
  delete(): void {
      this.inventoryService.deleteProduct(
          Number(this.route.snapshot.paramMap.get("id"))
          ).subscribe(product => this.product = product);
  }
}


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
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {

  /**
  * {@link Product} of interest.
  */
   @Input() product?: Product;

  constructor(
    private route: ActivatedRoute, 
    private inventoryService: InventoryService,
    private location: Location
  ) { }


  ngOnInit(): void {}

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
  add(
    name: string, price: string, quantity: string,
    description: string ): void 
  {
    name = name.trim();
    description = description.trim() ;
    

    if (!name) { return; }
    //this.inventoryService.addProduct({ name, price, quantity, description } as Product) ;
  }


}

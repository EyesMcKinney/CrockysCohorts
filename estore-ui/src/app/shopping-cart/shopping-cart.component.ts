import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { Observable, Subject } from 'rxjs';
/**
 * Provides methods to interact with individual {@linkplain ShoppingCart}.
 * 
 * @author Isaac McKinney
 */
@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  items = this.cartService.getItems();
  item:any;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    
  }

  /**
     * Delete the last{@link product Product} from Cart. 
     * 
     * @author Isaac McKinney
     */
  delete() {
    this.items.splice(this.items.indexOf(this.item), 1);
  }

  /**
   * Clear the cart by checking out
   */
  buy(){
    this.items = [];
    this.cartService.clearCart();
  }
}

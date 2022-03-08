import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { CartService } from '../cart.service';
import { Observable, Subject } from 'rxjs';

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

  delete() {
    this.items.splice(this.items.indexOf(this.item), 1);
  }

  buy(){
    this.items = [];
    this.cartService.clearCart();
  }
}

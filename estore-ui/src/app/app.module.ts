import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { ProductSearchComponent } from './product-search/product-search.component';
import { ProductInfoComponent } from './product-info/product-info.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { HeaderComponent } from './header/header.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { MessagesComponent } from './messages/messages.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: '', component: ProductInfoComponent },
      { path: 'products/:productId', component: ProductSearchComponent },
      { path: 'shopping-cart', component: ShoppingCartComponent },
    ])
  ],
  declarations: [
    AppComponent,
    ProductsComponent,
    ProductSearchComponent,
    ProductInfoComponent,
    HomepageComponent,
    LoginpageComponent,
    HeaderComponent,
    ShoppingCartComponent,
    MessagesComponent
  ],
  //providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { ProductSearchComponent } from './product-search/product-search.component';
import { ProductInfoComponent } from './product-info/product-info.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { AdminComponent } from './admin/admin.component';
import { HeaderRoutingModule } from './header-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    HeaderRoutingModule
  ],
  declarations: [
    AppComponent,
    ProductsComponent,
    ProductSearchComponent,
    ProductInfoComponent,
    HomepageComponent,
    LoginpageComponent,
    AdminComponent
  ],
  //providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

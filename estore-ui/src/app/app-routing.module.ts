import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { ProductsComponent } from './products/products.component';
import { ProductInfoComponent } from './product-info/product-info.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { AdminComponent } from './admin/admin.component';
import { ProductEditComponent } from './product-edit/product-edit.component';
import { ProductAddComponent } from './product-add/product-add.component';

const routes: Routes = [
  { path: '', redirectTo: '/homepage', pathMatch: 'full' },
  { path: 'homepage', component: HomepageComponent },
  { path: 'loginpage', component: LoginpageComponent },
  { path: 'admin', component: AdminComponent},
  { path: 'products', component: ProductsComponent },
  { path: 'info/:id', component: ProductInfoComponent },
  { path: 'edit/:id', component: ProductEditComponent },
  { path: 'add', component: ProductAddComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

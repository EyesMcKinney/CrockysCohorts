/**
 * Router information for application
 * 
 * @author Tylin Hartman
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { ProductInfoComponent } from './product-info/product-info.component';
import { LoginpageComponent } from './loginpage/loginpage.component';

const routes: Routes = [
  { path: '', redirectTo: '/homepage', pathMatch: 'full' },
  { path: 'homepage', component: HomepageComponent },
  { path: 'loginpage', component: LoginpageComponent },
  { path: 'info/:id', component: ProductInfoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

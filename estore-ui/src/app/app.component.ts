import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Crocy\'s Crochet';
  searchText: any;
  products = [
    {"id":2,"name":"Puff Sleeve Sweater","price":80.0,"quantity":3,"description":"Cropped sweater with puffed sleeves. Multiple colors available."},
    {"id":3,"name":"Elephant","price":15.0,"quantity":5,"description":"Grey elephant plush with tiny legs and large ears. 3yrs +"},
    {"id":4,"name":"Monkey","price":20.0,"quantity":2,"description":"Small monkey holding a banana"},
    {"id":5,"name":"Narwhal","price":30.0,"quantity":3,"description":"Large narwhal (14 inch diameter)"},
    {"id":6,"name":"Flower shirt","price":45.0,"quantity":1,"description":"Cropped corset back shirt with flower border on bottom edge"},
    {"id":7,"name":"Crocky's Crocodile","price":25.0,"quantity":1,"description":"Creen crocodile plush. 5 inches tall. 3yrs+"}
  ];
}

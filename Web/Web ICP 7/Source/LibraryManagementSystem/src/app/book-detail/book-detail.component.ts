import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../api.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  book = {
   /* isbn: undefined,
    author: undefined,
    publisher: undefined,
    published_year: undefined,
    description: undefined,
    title: undefined, _id: '/book-edit',*/


    updated_date() {

    }
  };

  constructor(private route: ActivatedRoute, private api: ApiService, private router: Router) {
  }

  ngOnInit() {
    this.getBookDetails(this.route.snapshot.params['id']);
  }

  getBookDetails(id) {
    this.api.getBook(id)
      .subscribe(data => {
        console.log(data);
        this.book = {...data};
      });
  }

  deleteBook(id) {
    this.api.deleteBook(id)
      .subscribe(res => {
          this.router.navigate(['/books']);
        }, (err) => {
          console.log(err);
        }
      );
  }

}

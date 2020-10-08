import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ApiService} from '../api.service';
import {FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
export class BookEditComponent implements OnInit {
  bookForm: FormGroup;
  book = { // creating an array to hold book information contents
    'isbn': '',
    'title': '',
    'description': '',
    'author': '',
    'publisher': '',
    'published_year': ''
  };
  matcher: any; // used for the html page formatted based off original code

  constructor(private router: Router, private route: ActivatedRoute, private api: ApiService, private formBuilder: FormBuilder) {
      this.bookForm = formBuilder.group({
        isbn: [this.book.isbn, Validators.required],
        title: [this.book.title, Validators.required],
        description: [this.book.description, Validators.required],
        author: [this.book.author, Validators.required],
        publisher: [this.book.publisher, Validators.required],
        published_year: [this.book.published_year, Validators.required]
      });
  }

  ngOnInit(): void { // this section is intended to retrieve the book information for updating from the api
    this.api.getBook(this.route.snapshot.params['id']).subscribe(data =>
    this.book = {...data});
  }

  onFormSubmit(form: NgForm) { // idea taken from your book-create.component.ts section. trying to update the info
    this.api.updateBook(this.route.snapshot.params['id'], form)
      .subscribe(res => {
        const id = res['_id']; // changed to constant
        this.router.navigate(['/book-details', id]);
      }, (err) => {
        console.log(err);
      });
  }


}

import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ApiService} from '../api.service';
import {FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
// NEW INFO ADDED HERE
export class BookEditComponent implements OnInit { // copied info from book-create section to use here.
  bookForm: FormGroup;
  isbn: string = '';
  title: string = '';
  description: string = '';
  author: string = '';
  publisher: string = '';
  published_year: string = '';
  book = {}; // used to save information added by user
  //matcher: any;

  constructor(private router: Router, private route: ActivatedRoute, private api: ApiService, private formBuilder: FormBuilder) {
  }

  // NEW INFO ADDED HERE
  ngOnInit() {
    //this.getBookDetails(this.route.snapshot.params['id']);

    // info retrieved from book-create.component file.
    this.bookForm = this.formBuilder.group({
      'isbn': [null, Validators.required],
      'title': [null, Validators.required],
      'description': [null, Validators.required],
      'author': [null, Validators.required],
      'publisher': [null, Validators.required],
      'published_year': [null, Validators.required]
    });
    this.getBook(this.route.snapshot.params['id']);
  }
//adding get book method here
  getBook(id) {
    this.api.getBook(id).subscribe(data => {
      id = data._id;
      this.bookForm.setValue({
        isbn: data.isbn,
        title: data.title,
        description: data.description,
        author: data.author,
        publisher: data.publisher,
        published_year: data.published_year
      });
    });
  }

// adding getBookDetails method here

getBookDetails(id) { // used to retrieve book id information from api
    this.api.getBook(id)
      .subscribe(data => {
        console.log(data);
        this.book = data;
      });
  }
// adding onFormSubmit here
  onFormSubmit(form: NgForm) { // this section should update the information when the user submits the changes
    console.log(form);
    this.api.updateBook(this.route.snapshot.params['id'], form)
      .subscribe( res => {
        this.router.navigate(['/book-details', this.route.snapshot.params['id']]); // updates should be made to this section of the api
      }, (err) => {
        console.log(err); // here to catch errors
      });
  }
}


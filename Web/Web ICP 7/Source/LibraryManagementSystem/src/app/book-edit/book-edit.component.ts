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
  isbn: string = '';
  title: string = '';
  description: string = '';
  author: string = '';
  publisher: string = '';
  published_year: string = '';

  book = {};

  constructor(private router: Router, private route: ActivatedRoute, private api: ApiService, private formBuilder: FormBuilder) {
  }

  ngOnInit(){ // this section is intended to retrieve the book information for updating from the api
    this.getBookDetails(this.route.snapshot.params['id']);
    this.bookForm = this.formBuilder.group({
      isbn: [null, Validators.required],
      title: [null, Validators.required],
      description: [null, Validators.required],
      author: [null, Validators.required],
      publisher: [null, Validators.required],
      published_year: [null, Validators.required]
    });
  }
getBookDetails(id){
    this.api.getBook(id)
      .subscribe(data => {
        console.log(data);
        this.book = data;
      });
}
  onFormSubmit(form: NgForm) { // idea taken from your book-create.component.ts section. trying to update the info
    this.api.updateBook(this.route.snapshot.params['id'], form)
      .subscribe(res => {
        const id = res['_id']; // changed to constant
        this.router.navigate(['/book-details', id]).then(r => {});
      }, (err) => {
        console.log(err);
      });
  }


}

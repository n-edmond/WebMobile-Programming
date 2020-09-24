import { Component } from '@angular/core';
import index from '@angular/cli';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // define list of items
  items = [];
  public newT;
  countDownDate = new Date('Dec 15 2020 23:59:59').getTime(); /*used for countdown timer*/
  demo: any;

  temp = setInterval(() => {
    var today = new Date().getTime();
    var future = this.countDownDate - today; // calculates how much time is left before set date
    var days = Math.floor(future / (1000 * 60 * 60 * 24)); // will calculate the number of days.
    var hours = Math.floor((future % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) ); // will calculate the number of hours.
    var minutes = Math.floor((future % (1000 * 60 * 60 )) / (1000 * 60 )); // will calculate the number of minutes.
    var seconds = Math.floor((future % (1000 * 60 )) / (1000)); // will calculate number of seconds

    this.demo = days + ' DAYS ' + hours + ' HOURS ' + minutes + ' MIN ' + seconds + ' SEC';

  });

  // Write code to push new item
  submitNewItem(newT) {// creates a new item with bool var set to false. Indicates not complete
    if (newT !== '') {
      this.items.push({val: this.newT, complete: false}); // adds to list and marks complete as false
      /*this.newT = ''; /*sets newT back to a blank character for future use */
    }
  }

  // Write code to complete item
  completeItem(td) {// indicates items are complete by changing bool to true
    for (let i = 0; i < this.items.length; i++) {
      if (this.items[i] === td) {
        this.items[i].complete = true; // setting the complete var to true.
      }
    }
  }

  // Write code to delete item
  deleteItem(i) { // removes item from array
    this.items.splice(i, 1); /*specifies index to change the array.This will delete the item*/
  }
}

function myFunction() {
  document.getElementById('demo').innerHTML = this.newT;
}

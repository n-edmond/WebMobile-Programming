import { Component } from '@angular/core';
import index from '@angular/cli';
import {$} from 'protractor';
import {style} from '@angular/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // define list of items
  public items = []; // array to hold to do items
  public newT; // public variable for access as needed
  countDownDate = new Date('Dec 15 2020 23:59:59').getTime(); /*used for countdown timer*/
  demo: any; // USED FOR COUNTDOWN

  temp = setInterval(() => { // calculates days,hours, minutes, etc.
    let today = new Date().getTime();
    let future = this.countDownDate - today; // calculates how much time is left before set date
    let days = Math.floor(future / (1000 * 60 * 60 * 24)); // will calculate the number of days.
    let hours = Math.floor((future % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) ); // will calculate the number of hours.
    let minutes = Math.floor((future % (1000 * 60 * 60 )) / (1000 * 60 )); // will calculate the number of minutes.
    let seconds = Math.floor((future % (1000 * 60 )) / (1000)); // will calculate number of seconds

    this.demo = days + ' DAYS ' + hours + ' HOURS ' + minutes + ' MIN ' + seconds + ' SEC'; // used for display in html

  });

  // Write code to push new item
  submitNewItem() {// creates a new item with bool var set to false. Indicates not complete
    if (this.newT === '') {
    } else {
      this.items.push(this.newT); // pushes user input into array
      this.newT = ''; // used for user input. Resets it back to blank
    }
  }

  // Write code to complete item
  completeItem(i) {
    // creates variable that retrieves element for where the to do list item appears
    let task = document.getElementsByClassName('col-9 text-black h3')as HTMLCollectionOf<HTMLElement>;
    task[i].style.setProperty('text-decoration', 'line-through'); // strikes through the word
  }

  // Write code to delete item
  deleteItem(i) { // removes item from array
    this.items.splice(i, 1); /*specifies index to change the array.This will delete the item*/
  }
}

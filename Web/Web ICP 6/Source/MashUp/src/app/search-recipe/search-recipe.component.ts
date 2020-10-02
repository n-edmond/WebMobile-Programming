import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Http, Response } from '@angular/http';
import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';

@Injectable()
export class MegaSuperService {
  constructor(private _http: HttpClient) {}
}

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList: any;
  recipeList = [];

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;



  constructor(private _http: HttpClient) {
  }

  public data;
  // tslint:disable-next-line:max-line-length
  private credentials = 'client_id=NGVHTOLBARR53FA3HJACBOQA3LJGBIJOSK45CXE5S420X3HF&client_secret=XMVXDPXCN1XLAPAR0YCWH24L4LD40I3YIAHZD3XL5XGA1L14';
  private rcredentials = 'app_id=33bd6a80&app_key=2c385b95519f0dfd3456a6c4eefc87ed';

  ngOnInit() {
    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });

  }


  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {

      // used to excuse the limit error
      // tslint:disable-next-line:max-line-length
      this._http.get('https://api.edamam.com/search?q=' + this.recipeValue + '&' + this.rcredentials + '&from=0&to=10&calories=591-722&health=alcohol-free'
      ).subscribe(result => {
        this.recipeList = result['hits']; // saves the reciepe list as an obj.

        console.log(this.recipeList); // displays the recipe list as an object. used for testing
        },
        error => { // error handling. nothing here.
      });
    }
/*the same process as above is done here. We are instead acccessing venue locations similar to the food value*/
    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      // tslint:disable-next-line:max-line-length
      this._http.get('https://api.foursquare.com/v2/venues/explore&' + this.credentials +  '&v=20173009&&limit=10m&near=' + this.placeValue + '&query=' + this.recipeValue +
    '').subscribe(result => {
          this.venueList = result['response'];
          console.log(this.venueList); // displays for testing
          console.log(result); // displays for testing.
        },
        error => { // error handling here

        });

      }
  }
}

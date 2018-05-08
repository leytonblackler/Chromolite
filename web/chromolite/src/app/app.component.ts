/**
 *  Chromolite App Component
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

/* Angular Imports */
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})

/**
 *  Initialises the Angular app structure and provides the Angular router
 *  for navigation between components within the application.
 *  
 *  @author Riley Blair (GitHub: Riley-Blair)
 */
export class AppComponent {
    title = 'app';

    /**
     *  Initialises the Angular router for navigation between components
     *  within the application. This router is passed to all child components
     *  in order to perform the desired navigation.
     *
     *  @param route - Angular component router.
     */
    constructor(public route: Router) {

    }
}

/**
 *  Chromolite Dashboard Component
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

/* Angular Imports */
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})

/**
 *  Dashboard Component
 *
 *  Provides the primary user interface that the user interacts with to control the
 *  Chromolite application and connected RGB LED devices.
 *
 *  @author Riley Blair (GitHub: Riley-Blair)
 */
export class DashboardComponent {

    /**
     *  Performs all necessary initialisation for the application and sets up the
     *  necessary variables required for communication with the server via HTTP.
     *
     *  @param route - Angular component router.
     *  @param activeRoute - The currently active route of Angular's component router.
     *  @param http - HTTP client for external authentication requests.
     */
    constructor(private route: Router, private activeRoute: ActivatedRoute, private http: HttpClient) {

    }
}

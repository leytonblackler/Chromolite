/**
 *  Chromolite Phillips Hue Component
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

/* Angular Imports */
import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

/* Parent Component Imports */
import { DashboardComponent } from '../../dashboard/dashboard.component';

@Component({
    selector: 'app-phillips-hue',
    templateUrl: './phillips-hue.component.html',
    styleUrls: ['./phillips-hue.component.css']
})

/**
 *  Phillips Hue Component
 *
 *  Phillips Hue settings interface for controlling connected Phillips Hue IoT devices remotely.
 *
 *  @author Riley Blair (GitHub: Riley-Blair)
 */
export class PhillipsHueComponent {

    /**
     *  Gathers current Phillips Hue settings and initialises the component based upon these.
     *  Also sets up the necessary variables required for communication with the Phillips Hue
     *  devices via HTTP.
     *
     *  @param http - HTTP client for external authentication requests.
     *  @param parent - The dashboard component that this component is rendered inside.
     */
    constructor(private http: HttpClient, public parent: DashboardComponent) {

    }
}

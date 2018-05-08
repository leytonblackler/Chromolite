/**
 *  Chromolite Razer Chroma Component
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
    selector: 'app-razer-chroma',
    templateUrl: './razer-chroma.component.html',
    styleUrls: ['./razer-chroma.component.css']
})

/**
 *  Razer Chroma Component
 *
 *  Razer Chroma settings interface for controlling connected Razer Chroma peripherals remotely.
 *
 *  @author Riley Blair (GitHub: Riley-Blair)
 */
export class RazerChromaComponent {

    /**
     *  Gathers current Razer Chroma settings and initialises the component based upon these.
     *  Also sets up the necessary variables required for communication with the Razer Chroma
     *  devices via HTTP.
     *
     *  @param http - HTTP client for external authentication requests.
     *  @param parent - The dashboard component that this component is rendered inside.
     */
    constructor(private http: HttpClient, public parent: DashboardComponent) {

    }
}

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
import { NgForOf } from '@angular/common';

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
    allSettings: any;
    settings: any;

    /**
     *  Performs all necessary initialisation for the application and sets up the
     *  necessary variables required for communication with the server via HTTP.
     *
     *  @param route - Angular component router.
     *  @param activeRoute - The currently active route of Angular's component router.
     *  @param http - HTTP client for external authentication requests.
     */
    constructor(private route: Router, private activeRoute: ActivatedRoute, private http: HttpClient) {
        this.allSettings = {
            'modes': [
                { 'name': 'static', 'enabled': true },
                { 'name': 'cycle', 'enabled': true },
                { 'name': 'wave', 'enabled': true },
                { 'name': 'music', 'enabled': false },
                { 'name': 'scan', 'enabled': true },
                { 'name': 'strobe', 'enabled': false },
                { 'name': 'off', 'enabled': true },
                { 'name': 'disable', 'enabled': true }
            ],
            'platforms': [
                { 'name': 'arduino', 'enabled': true, 'icon': 'arduino_logo' },
                { 'name': 'razer-chroma', 'enabled': true, 'icon': 'razer_logo' },
                { 'name': 'phillips-hue', 'enabled': false, 'icon': 'hue_logo' }
            ],
            'general': [
                { 'name': 'save-settings', 'enabled': false },
                { 'name': 'load-settings', 'enabled': false },
                { 'name': 'reset-to-default', 'enabled': true }
            ]
        };

        this.settings = {
            'colours': {
                'primary': {'red': 255, 'green': 0, 'blue': 0},
                'secondary': {'red': 0, 'green': 255, 'blue': 0},
                'tertiary': {'red': 0, 'green': 0, 'blue': 255}
            },
            'mode': 'static',
            'brightness': 100,
            'style': 'solid',
            'numberOfColours': 1,
            'platforms': {
                'arduino': false,
                'razer chroma': false,
                'phillips hue': false
            },
            'sync': false,
            'minimised': false
        };
    }

    /**
     *  Sets the user's preferred lighting mode based upon the index of the mode within
     *  the allSettings object. This also performs a check to make sure that the mode is
     *  enabled so that the user cannot select a mode that is not yet supported.
     *
     *  @param index - The index of the chosen mode within the allSettings.modes array.
     */
    setMode(index: number): void {
        if (this.allSettings.modes[index].enabled) {
            this.settings.mode = this.allSettings.modes[index].name;
        }
    }
}

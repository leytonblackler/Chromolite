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
    defaultSettings: any;

    ipAddress: string;
    port: string;

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
        this.defaultSettings = {
            'colours': {
                'primary': {'red': 255, 'green': 0, 'blue': 0},
                'secondary': {'red': 0, 'green': 255, 'blue': 0},
                'tertiary': {'red': 0, 'green': 0, 'blue': 255}
            },
            'mode': 'static',
            'brightness': 100,
            'speed': 50,
            'numberOfColours': 1,
            'platforms': {
                'arduino': true,
                'razer-chroma': false,
                'phillips-hue': false
            },
            'sync': false,
            'minimised': false
        };
        this.settings = this.defaultSettings;
        this.ipAddress = "localhost";
        this.port = "8080";
    }

    /**
     *  Sets the user's preferred lighting mode based upon the index of the mode within
     *  the allSettings object. This also performs a check to make sure that the mode is
     *  enabled so that the user cannot select a mode that is not yet supported.
     *
     *  @param index - The index of the chosen mode within the allSettings.modes array.
     */
    setMode(index: number): void {
        let mode = this.allSettings.modes[index];
        if (mode.enabled) {
            this.settings.mode = mode.name;
            this.updateGlobalSettings()
        }
    }

    /**
     *  Sets the specified platform to be either active or inactive based upon the previous
     *  status. The REST API endpoint is then hit in order to update the output.
     *
     *  @param index - The index of the chosen platform within the allSettings.platforms array.
     */
    setPlatform(index: number): void {
        let platform = this.allSettings.platforms[index];
        if (platform.enabled) {
            this.settings.platforms[platform.name]=!this.settings.platforms[platform.name];
            this.updateGlobalSettings();
        }
    }

    /**
     *  Performs the necessary functionality when a general settings button is clicked.
     *  The outcome of this depends on the button that was clicked. Currently the only
     *  button that is enabled is the reset to default option.
     *
     *  @param settingsName - The name of the clicked setting as specified within the
     *                        allSettings object.
     */
    generalSettingsClicked(settingName: string): void {
        if (settingName == 'reset-to-default') {
            this.settings = this.defaultSettings;
            this.updateGlobalSettings();
        }
    }

    /**
     *  Sends a request to the REST API web server for the global settings to be updated.
     *  Global requests
     */
    updateGlobalSettings(): void {
        let url = this.ipAddress == "localhost" ? "http://localhost:" : this.ipAddress + ":"
        this.http.post(url + this.port + '/global', this.settings).subscribe((res: any) => {
            console.log("REST API Request successful. Settings updated for the following outputs: ");
            if (res.platforms['arduino']) { console.log("Arduino"); }
            if (res.platforms['phillips-hue']) { console.log("Phillips Hue"); }
            if (res.platforms['razer-chroma']) { console.log("Razer Chroma"); }
        }, err => {
            console.log("Error: ", err)
        });
    }
}

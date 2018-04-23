/**
 *  Chromolite Spectrum Component
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

/* Angular Imports */
import { Component } from '@angular/core';

/* Parent Component Imports */
import { DashboardComponent } from '../dashboard/dashboard.component';

@Component({
    selector: 'app-spectrum',
    templateUrl: './spectrum.component.html',
    styleUrls: ['./spectrum.component.css']
})

/**
 *  Spectrum Component
 *
 *  Provides an interface for the user to select primary, secondary, and tertiary
 *  colours through that will be used with the specific mode and pattern to be
 *  output to the connected devices suporting customisation and controm of RGB LEDs.
 *
 *  @author Riley Blair (GitHub: Riley-Blair)
 */
export class SpectrumComponent {

    /**
     *  Initialises the RGB colour palette for the user to select their desirder
     *  primary, secondary, and tertiary colours from. The colours are initially
     *  set to their default values.
     *
     *  @param parent - The dashboard component that this component is rendered inside.
     */
    constructor(public parent: DashboardComponent) {

    }
}

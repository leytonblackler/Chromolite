/**
 *  Chromolite Simulation Component
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
    selector: 'app-simulation',
    templateUrl: './simulation.component.html',
    styleUrls: ['./simulation.component.css']
})

/**
 *  Simulation Component
 *
 *  Provides a simulation of how the user's chosen settings will appear on connected
 *  devices that support RGB LED customisation and control.
 *
 *  @author Riley Blair (GitHub: Riley-Blair)
 */
export class SimulationComponent {

    leds: number[];

    /**
     *  Initialises the RGB LED simulation to reflect the default Chromolite settings.
     *
     *  @param parent - The dashboard component that this component is rendered inside.
     */
    constructor(public parent: DashboardComponent) {
        this.onPageResize();
    }

    /**
     *  Resize function that is triggered whenever the simulation component's page container is
     *  resized. From this we can access the current page width and height and perform any
     *  necessary functions depending on the size of the page.
     */
    onPageResize(): void {
        this.leds = [];
        for (let index = 0; index < (window.innerWidth - 40) / 20 - 1; index++) {
            this.leds.push(index)
        }
    }
}

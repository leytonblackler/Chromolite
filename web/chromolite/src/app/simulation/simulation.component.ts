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

    /**
     *  Initialises the RGB LED simulation to reflect the default Chromolite settings.
     *
     *  @param parent - The dashboard component that this component is rendered inside.
     */
    constructor(public parent: DashboardComponent) {

    }
}

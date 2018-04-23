/**
 *  Chromolite App Module
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

/* Angular Imports */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

/* Component Imports */
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SpectrumComponent } from './spectrum/spectrum.component';
import { SimulationComponent } from './simulation/simulation.component';
import { ArduinoComponent } from './settings/arduino/arduino.component';
import { RazerChromaComponent } from './settings/razer-chroma/razer-chroma.component';
import { PhillipsHueComponent } from './settings/phillips-hue/phillips-hue.component';

/* Pipe Imports */
import { RemoveHyphensPipe } from './pipes/remove-hyphens.pipe';

/* Routing Definitions */
const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: DashboardComponent,
        children: [
            { path: '', component: SpectrumComponent, outlet: 'spectrum' },
            { path: '', component: SimulationComponent, outlet: 'simulation' },
            { path: 'settings/arduino', component: ArduinoComponent, outlet: 'modal' },
            { path: 'settings/razer-chroma', component: RazerChromaComponent, outlet: 'modal' },
            { path: 'settings/phillips-hue', component: PhillipsHueComponent, outlet: 'modal' },
        ]
    },
    { path: '**', redirectTo: '' }
];

@NgModule({
    declarations: [
        AppComponent,
        DashboardComponent,
        SpectrumComponent,
        SimulationComponent,
        ArduinoComponent,
        RazerChromaComponent,
        PhillipsHueComponent,
        RemoveHyphensPipe
    ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes, {initialNavigation: true}),
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})

export class AppModule { }

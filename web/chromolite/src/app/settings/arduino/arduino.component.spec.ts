/**
 *  Chromolite Arduino Component Specifications
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArduinoComponent } from './arduino.component';

describe('ArduinoComponent', () => {
    let component: ArduinoComponent;
    let fixture: ComponentFixture<ArduinoComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ ArduinoComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ArduinoComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});

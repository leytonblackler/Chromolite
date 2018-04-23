/**
 *  Chromolite Phillips Hue Component Specifications
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PhillipsHueComponent } from './phillips-hue.component';

describe('PhillipsHueComponent', () => {
    let component: PhillipsHueComponent;
    let fixture: ComponentFixture<PhillipsHueComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ PhillipsHueComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(PhillipsHueComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});

/**
 *  Chromolite Spectrum Component Specifications
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpectrumComponent } from './spectrum.component';

describe('SpectrumComponent', () => {
    let component: SpectrumComponent;
    let fixture: ComponentFixture<SpectrumComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ SpectrumComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SpectrumComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});

/**
 *  Chromolite Razer Chroma Component Specifications
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RazerChromaComponent } from './razer-chroma.component';

describe('RazerChromaComponent', () => {
    let component: RazerChromaComponent;
    let fixture: ComponentFixture<RazerChromaComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ RazerChromaComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(RazerChromaComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});

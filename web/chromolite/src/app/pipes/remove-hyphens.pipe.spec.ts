/**
 *  Chromolite Remove Hyphens Pipe Specifications
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

import { RemoveHyphensPipe } from './remove-hyphens.pipe';

describe('RemoveHyphensPipe', () => {
    it('create an instance', () => {
        const pipe = new RemoveHyphensPipe();
        expect(pipe).toBeTruthy();
    });
});

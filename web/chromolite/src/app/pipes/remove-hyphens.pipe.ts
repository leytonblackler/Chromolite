/**
 *  Chromolite Remove Hyphens Pipe
 *  Copyright (c) 2018
 *
 *  Last Updated: 23/04/2018
 *  Author: Riley Blair - https://github.com/Riley-Blair
 */

/* Angular Imports */
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'removeHyphens'
})

/**
 *  Remove Hyphens Pipe
 *
 *  Removes all hypens from a given input string. Useful for making routing
 *  & variable names more aligned.
 *
 *  @author Riley Blair (GitHub: Riley-Blair)
 */
export class RemoveHyphensPipe implements PipeTransform {

    /**
     *  Simple method to replace alll hyphens within a string with a space instead.
     *
     *  @param value - A string that is expected to contain at lease one hyphen.
     *
     *  @return The input string without any hypens in it. Hyphens are all replaced
     *          by single spaces.
     */
    transform(value: string): string {
        return value.replace(/-/g, ' ');
    }
}

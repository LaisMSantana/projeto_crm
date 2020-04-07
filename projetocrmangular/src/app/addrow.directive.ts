import { Directive, Input, HostListener } from '@angular/core';

@Directive({
  selector: '[appAddrow]'
})
export class AddrowDirective {
  @Input() newRow: any;

  @HostListener('click', ['$event'])
  onClick(event: Event){

  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ItemVenda } from '../models/item-venda';

@Injectable({
  providedIn: 'root'
})
export class ItemVendaService{


  constructor(private http: HttpClient){}

}

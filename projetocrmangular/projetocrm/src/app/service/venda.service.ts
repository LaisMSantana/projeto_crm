import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VendaModel } from '../venda-model';
import { ItemProduto } from '../item-produto';

@Injectable({
  providedIn: 'root'
})
export class VendaService {
  formData: VendaModel;
  itensProduto: ItemProduto[];

  url = 'http://localhost:8080/api/vendas';

  constructor(private http: HttpClient) { }

}

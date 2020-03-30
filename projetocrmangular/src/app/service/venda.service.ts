import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VendaModel } from '../models/venda-model';
import { ItemProduto } from '../models/item-produto';

@Injectable({
  providedIn: 'root'
})
export class VendaService {
  formData: VendaModel = new VendaModel();
  itensProduto: ItemProduto[] = new Array<ItemProduto>();

  url = 'http://localhost:8080/api/vendas';

  constructor(private http: HttpClient) { }

  salvarVenda(){
    var body ={
      ...this.formData,
      produtos : this.itensProduto
    }
    return this.http.post(this.url, body);
  }
}

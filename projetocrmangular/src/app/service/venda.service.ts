import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VendaModel } from '../models/venda-model';
import { ItemVenda } from '../models/item-venda';

@Injectable({
  providedIn: 'root'
})
export class VendaService {
  formData: VendaModel = new VendaModel();
  formItem: ItemVenda = new ItemVenda();
  itensVenda: ItemVenda[] = new Array<ItemVenda>();

  url = 'http://localhost:8080/api/vendas';

  constructor(private http: HttpClient) { }

  salvarVenda(){
    var body ={
      ...this.formData,
      itens : this.itensVenda
    }
    console.log(body);
    return this.http.post(this.url, body);
  }

  updateVenda(){
    var body ={
      ...this.formData,
      itens : this.itensVenda
    }
    this.http.put<VendaModel>(this.url + '/' + this.formData.idVenda, body).subscribe();
  }

  getVenda(id : number){
    this.http.get(this.url + '/venda/' + id).subscribe((data: VendaModel) => this.formData = data);
    this.http.get(this.url + '/itemVenda/' + id).subscribe((data: ItemVenda[]) => this.itensVenda = data);
  }
}

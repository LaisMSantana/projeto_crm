import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ItemProduto } from '../models/item-produto';
import { ProdutoModel } from '../models/produto-model';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  formData:ItemProduto = new ItemProduto();

  url = 'http://localhost:8080/api/produtos';

  constructor(private http: HttpClient) { }

  getProdutos(){
    return this.http.get(this.url).toPromise();
  }
}

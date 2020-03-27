import { Component, OnInit, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { ItemProduto } from '../item-produto';
import { HttpClient } from '@angular/common/http';
import { ProdutoModel } from '../produto-model';
import { NgForm } from '@angular/forms';
import { VendaService } from '../service/venda.service';
import { ProdutoService } from '../service/produto.service';

@Component({
  selector: 'app-item-produto',
  templateUrl: './item-produto.component.html',
  styleUrls: ['./item-produto.component.css']
})
export class ItemProdutoComponent implements OnInit {
  formData:ItemProduto = new ItemProduto();
  listaProdutos: any = [];
  isValid: boolean = true;

  url = 'http://localhost:8080/api/produtos';

  constructor(private http: HttpClient,
    @Inject(MAT_DIALOG_DATA) public data,
    public dialogRef:MatDialogRef<ItemProdutoComponent>,
    private vendaService:VendaService,
    private produtoService: ProdutoService) { }

  ngOnInit(){
    this.produtoService.getProdutos().then(res => this.listaProdutos = res as Array<ProdutoModel>);
    if(this.data.itemProdutoIndex==null)
    this.formData ={
      idItemProduto: null,
      idVenda : this.data.idVenda,
      idProduto:0,
      quantidade:0,
      itemNome:''
    }
    else
    this.formData = Object.assign({},this.vendaService.itensProduto[this.data.itemProdutoIndex]);
  }

  getProdutos(){
    return this.http.get(this.url).toPromise();
  }

  atualizarNomeItem(ctrl){
    if(ctrl.selectedIndex == 0) {
      this.formData.itemNome = '';
    }else{
      this.formData.itemNome = this.listaProdutos[ctrl.selectedIndex-1].nome;
    }
  }

  onSubmit(form:NgForm){
    if(this.validarForm(form.value)){
      if(this.data.itemProdutoIndex==null)
      this.vendaService.itensProduto.push(form.value);
      else
        this.vendaService.itensProduto[this.data.itemProdutoIndex] = form.value;
      this.dialogRef.close();
    }
  }

  validarForm(formData: ItemProduto){
    this.isValid = true;
    if(formData.idItemProduto == 0)
      this.isValid = false;
    else if(formData.quantidade == 0)
      this.isValid = false;
    return this.isValid;
  }

}

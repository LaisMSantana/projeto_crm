import { Component, OnInit, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import { ItemProduto } from '../item-produto';
import { HttpClient } from '@angular/common/http';
import { ProdutoModel } from '../produto-model';
import { NgForm } from '@angular/forms';
import { VendaComponent } from '../venda/venda.component';

@Component({
  selector: 'app-item-produto',
  templateUrl: './item-produto.component.html',
  styleUrls: ['./item-produto.component.css']
})
export class ItemProdutoComponent implements OnInit {
  formData:ItemProduto;
  listaProdutos: ProdutoModel[];
  isValid: boolean = true;

  url = 'http://localhost:8080/api/produtos';

  constructor(private http: HttpClient,
    @Inject(MAT_DIALOG_DATA) public data,
    public dialogRef:MatDialogRef<ItemProdutoComponent>,
    private vendaComponent:VendaComponent) { }
   // private vendaComponent:VendaComponent

  ngOnInit(){
    this.getProdutos().then(res => this.listaProdutos = res as ProdutoModel[]);
    if(this.data.itemProdutoIndex==null)
    this.formData ={
      IDITEMPRODUTO: null,
      IDVENDA : this.data.IDVENDA,
      IDPRODUTO:0,
      QUANTIDADE:0,
      ITEMNOME:''
    }
    else
    this.formData = Object.assign({},this.vendaComponent.itemProduto[this.data.itemProdutoIndex]);
  }
  //,this.vendaComponent.itemProduto[this.data.itemProdutoIndex]
  getProdutos(){
    return this.http.get(this.url).toPromise();
  }

  atualizarNomeItem(ctrl){
    if(ctrl.selectedIndex == 0) {
      this.formData.ITEMNOME = '';
    }else{
      this.formData.ITEMNOME = this.listaProdutos[ctrl.selectedIndex-1].NOME;
    }
  }

  onSubmit(form:NgForm){
    if(this.validarForm(form.value)){
      //if(this.data.itemProdutoIndex==null)
      //this.vendaComponent.itemProduto.push(form.value);
      //else
        //this.vendaComponent.itemProduto[this.data.itemProdutoIndex] = form.value;
      this.dialogRef.close();
    }
  }

  validarForm(formData: ItemProduto){
    this.isValid = true;
    if(formData.IDITEMPRODUTO == 0)
      this.isValid = false;
    else if(formData.QUANTIDADE == 0)
      this.isValid = false;
    return this.isValid;
  }

}

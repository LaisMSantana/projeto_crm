import { Component, OnInit } from '@angular/core';
import { VendaModel } from '../venda-model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ItemProdutoComponent } from '../item-produto/item-produto.component';
import { VendaService } from '../service/venda.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-venda',
  templateUrl: './venda.component.html',
  styleUrls: ['./venda.component.css']
})
export class VendaComponent implements OnInit {

  constructor(private dialog:MatDialog,
    public service: VendaService,
    private router: Router){}

    resetForm(userForm?: NgForm){
        userForm.resetForm();
        this.service.formData = {
          IDVENDA: null,
          IDCLIENTE: 0,
          FORMADEPAGAMENTO: '',
          VALOR: 0
        };
        this.service.itensProduto= [];

    }

  AddouEditarItem(itemProdutoIndex, idVenda){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.data = {itemProdutoIndex, idVenda};
    this.dialog.open(ItemProdutoComponent, dialogConfig);
  }

  ngOnInit() {
    this.resetForm();
  }

  onDeleteItem(IdItemProduto: number, i: number){
    this.service.itensProduto.splice(i,1);
  }

}

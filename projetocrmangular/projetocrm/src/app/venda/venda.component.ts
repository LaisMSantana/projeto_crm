import { Component, OnInit } from '@angular/core';
import { VendaModel } from '../venda-model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ItemProdutoComponent } from '../item-produto/item-produto.component';
import { VendaService } from '../service/venda.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Cliente } from '../cliente';
import { ClienteService } from '../service/cliente.service';
import { ItemProduto } from '../item-produto';

@Component({
  selector: 'app-venda',
  templateUrl: './venda.component.html',
  styleUrls: ['./venda.component.css']
})
export class VendaComponent implements OnInit {
  listaClientes: any = [];

  constructor(private dialog:MatDialog,
    public service: VendaService,
    private router: Router,
    private clienteService: ClienteService){}

    resetForm(userForm?: NgForm){
        this.service.formData = new VendaModel();
        this.service.itensProduto= new Array<ItemProduto>();
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
    this.clienteService.getListaClientes().then(res => this.listaClientes = res as Array<Cliente>);
  }

  onDeleteItem(IdItemProduto: number, i: number){
    this.service.itensProduto.splice(i,1);
  }

}

import { Component, OnInit } from '@angular/core';
import { VendaModel } from '../models/venda-model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { VendaService } from '../service/venda.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Cliente } from '../models/cliente';
import { ClienteService } from '../service/cliente.service';
import { ItemVenda } from '../models/item-venda';

@Component({
  selector: 'app-venda',
  templateUrl: './venda.component.html',
  styleUrls: ['./venda.component.css']
})
export class VendaComponent implements OnInit {
  listaClientes: any = [];
  isValid: Boolean = true;
  errorMsg = '';
  succsessMsg = '';
  hideSuccessMessage = false;

  constructor(private dialog:MatDialog,
    public service: VendaService,
    private router: Router,
    private clienteService: ClienteService){}

    resetForm(userForm?: NgForm){
        this.service.formData = new VendaModel();
        this.service.itensVenda= new Array<ItemVenda>();
    }

  AddouEditarItem(itemProdutoIndex, idVenda){
  }

  ngOnInit() {
    this.clienteService.getListaClientes().then(res => this.listaClientes = res as Array<Cliente>);
  }

  onDeleteItem(IdItemProduto: number, i: number){
    this.service.itensVenda.splice(i,1);
  }

  validateForm(){
    this.isValid = true;
    if(this.service.formData.idCliente==0){
      this.isValid = false;
    }else if(this.service.itensVenda.length==0){
        this.isValid = false;
      }
      return this.isValid;
  }

  onSubmit(form: NgForm){
    if(this.validateForm()){
      this.service.salvarVenda().subscribe(res => {
        this.resetForm(),
        data => this.succsessMsg = "Venda salva com sucesso!",
        error => this.errorMsg = error.statusText
      });
    }
  }

  FadeOutSuccessMsg() {
    setTimeout( () => {
          this.hideSuccessMessage = true;
       }, 3000);
 }

}

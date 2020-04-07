import { Component, OnInit } from '@angular/core';
import { VendaModel } from '../models/venda-model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { VendaService } from '../service/venda.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Cliente } from '../models/cliente';
import { ClienteService } from '../service/cliente.service';
import { ItemVenda } from '../models/item-venda';
import { MarcaService } from '../service/marca.service';
import { CategoriaService } from '../service/categoria.service';
import { Marca } from '../models/marca';
import { Categoria } from '../models/categoria';

@Component({
  selector: 'app-venda',
  templateUrl: './venda.component.html',
  styleUrls: ['./venda.component.css']
})
export class VendaComponent implements OnInit {
  listaClientes: any = [];
  listaMarcas: any = [];
  listaCategorias: any = [];
  valorTotal: number = null;

  isValid: Boolean = true;
  isCreditoSelected: boolean;

  errorMsg = '';
  succsessMsg = '';
  hideSuccessMessage = false;

  constructor(public service: VendaService,
    private router: Router,
    private clienteService: ClienteService,
    private marcaService: MarcaService,
    private categoriaService: CategoriaService){}

  resetForm(userForm?: NgForm){
        this.service.formData = new VendaModel();
        this.service.itensVenda= new Array<ItemVenda>();
    }

  addFieldValue() {
      this.service.itensVenda.push(this.service.formItem)
      this.service.formItem = new ItemVenda();
  }

  deleteFieldValue(index) {
      this.service.itensVenda.splice(index, 1);
  }

  ngOnInit() {
    this.clienteService.getListaClientes().then(res => this.listaClientes = res as Array<Cliente>);
    this.marcaService.getListaMarcas().then(res => this.listaMarcas = res as Array<Marca>);
    this.categoriaService.getListaCategorias().then(res => this.listaCategorias = res as Array<Categoria>);
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

 selectInput(event) {
  let selected = event.target.value;
  if (selected == "Credito") {
    this.isCreditoSelected = true;
  } else {
    this.isCreditoSelected = false;
  }
}

updateValor(){
  if(this.service.formData.valor){
    this.service.formData.valor += parseFloat((this.service.formItem.valor * this.service.formItem.quantidade).toFixed(2));
  } else {
    this.service.formData.valor = parseFloat((this.service.formItem.valor * this.service.formItem.quantidade).toFixed(2));
  }
}

updateTotal(){
}

}

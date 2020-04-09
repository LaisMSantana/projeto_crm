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
  titulo: string;

  isValid: Boolean = true;
  isCreditoSelected: boolean;

  errorMsg = '';
  succsessMsg = '';
  hideSuccessMessage = false;

  constructor( public service: VendaService,
    private router: Router,
    private clienteService: ClienteService,
    private marcaService: MarcaService,
    private categoriaService: CategoriaService,
    private _route: ActivatedRoute){}

  resetForm(userForm?: NgForm){
        this.service.formData = new VendaModel();
        this.service.itensVenda= new Array<ItemVenda>();
    }

  addFieldValue() {
      this.updateValor(null);
      this.service.itensVenda.push(this.service.formItem);
      this.service.formItem = new ItemVenda();
  }

  deleteFieldValue(index, item) {
      this.service.itensVenda.splice(index, 1);
      this.deleteValor(item);
  }

  ngOnInit() {
    this._route.paramMap.subscribe(parameterMap =>{
      const id = +parameterMap.get('id');
      this.getVenda(id)
    });
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
    this._route.paramMap.subscribe(parameterMap =>{
      const id = +parameterMap.get('id');
    if(id == 0){
      if(this.validateForm()){
        this.service.salvarVenda().subscribe(res => {
          this.resetForm()
        });
      }
    } else{
      this.service.updateVenda();
      this.router.navigate(['/listagem-vendas']).then(() => {
        window.location.reload();
      });
      }
    });
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

updateValor(item){
  if(item == null){
    if(this.service.formData.valor){
      this.service.formData.valor += parseFloat((this.service.formItem.valor * this.service.formItem.quantidade).toFixed(2));
    } else {
      this.service.formData.valor = parseFloat((this.service.formItem.valor * this.service.formItem.quantidade).toFixed(2));
    }
  } else {
    if(this.service.formData.valor){
      this.service.formData.valor += parseFloat((item.valor * item.quantidade).toFixed(2));
      console.log(this.service.formData.valor);
    } else {
      this.service.formData.valor = parseFloat((item.valor * item.quantidade).toFixed(2));
    }
  }

}

deleteValor(item){
  this.service.formData.valor -= item.valor * item.quantidade;
  console.log(this.service.formData.valor);
}

onChange(item) {
  this.deleteValor(item);
  this.updateValor(item);
}

getVenda(id : number){
  if(id == 0){
    this.service.formData = new VendaModel();
    this.titulo = 'Nova Venda';
  } else {
    this.service.getVenda(id);
    this.titulo = 'Editar Venda';
  }
}

}

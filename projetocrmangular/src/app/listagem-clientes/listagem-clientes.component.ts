import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormArray, FormGroup, FormControl } from '@angular/forms';
import { Cliente } from '../models/cliente';

@Component({
  selector: 'app-listagem-clientes',
  templateUrl: './listagem-clientes.component.html',
  styleUrls: ['./listagem-clientes.component.css']
})
export class ListagemClientesComponent implements OnInit {

  headElements = ['Nome', 'CPF', 'Email', 'Data de Nascimento'];
  clientes: any = [];
  editField: string;
  errorMsg = '';
  succsessMsg = '';
  searchkey= '';
  hideSuccessMessage = false;

  controls: FormArray;

  url = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient,
    private router: Router){}


  ngOnInit(){
    return this.http.get(this.url).subscribe((data: any[]) =>{
      this.clientes = data;
      console.log(this.clientes);
    });

}

editarItem(cliente: Cliente){
  this.router.navigate(['/cliente', cliente.idCliente]);
}

cadastrarNovo(){
  this.router.navigate(['/cliente']);
}
applyFilter(filtro: string){
  if(filtro.includes("/")){
    filtro = filtro.split("/").join("");
    console.log(filtro);
  }
  return this.http.get(this.url + '/' + filtro.trim().toLocaleLowerCase()).subscribe((data) =>{
    this.clientes = data;
  });
}

FadeOutSuccessMsg() {
  setTimeout( () => {
        this.hideSuccessMessage = true;
     }, 3000);
}


}

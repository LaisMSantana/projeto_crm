import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClienteComponent } from '../cliente/cliente.component';
import { Router } from '@angular/router';
import { FormArray, FormGroup, FormControl } from '@angular/forms';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-listagem-clientes',
  templateUrl: './listagem-clientes.component.html',
  styleUrls: ['./listagem-clientes.component.css']
})
export class ListagemClientesComponent implements OnInit {

  headElements = ['Nome', 'CPF', 'Email'];
  clientes: any = [];
  editField: string;

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
updateList(id: number, property: string, event: any) {
  const editField = event.target.textContent;
  this.clientes[id][property] = editField;
}
changeValue(id: number, property: string, event: any) {
  this.editField = event.target.textContent;
}

editarItem(cliente: Cliente){
  return this.http.put<Cliente>(this.url + '/'  + cliente.idCliente, cliente).subscribe();
}

applyFilter(filterValue: string){
  return this.http.get(this.url + '/' +filterValue.trim().toLocaleLowerCase()).subscribe((data) =>{
    this.clientes = data;
  });
}


}

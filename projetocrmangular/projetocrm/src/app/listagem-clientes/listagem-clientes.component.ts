import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClienteComponent } from '../cliente/cliente.component';

@Component({
  selector: 'app-listagem-clientes',
  templateUrl: './listagem-clientes.component.html',
  styleUrls: ['./listagem-clientes.component.css']
})
export class ListagemClientesComponent implements OnInit {

  headElements = ['Nome', 'CPF', 'Email'];
  clientes: any = [];

  url = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient,){}


  ngOnInit(){
    return this.http.get(this.url).subscribe((data: any[]) =>{
      this.clientes = data;
      console.log(this.clientes);
    });
}

editarItem(id){

}

applyFilter(filterValue: string){
  return this.http.get(this.url + '/' +filterValue.trim().toLocaleLowerCase()).subscribe((data) =>{
    this.clientes = data;
  });
}


}

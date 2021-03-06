import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from '../models/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService{

  url = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient){}

  getListaClientes(){
    return this.http.get(this.url).toPromise();
  }


}

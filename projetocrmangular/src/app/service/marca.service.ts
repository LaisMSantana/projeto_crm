import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MarcaService{
  url = 'http://localhost:8080/api/marcas';

  constructor(private http: HttpClient){}

  getListaMarcas(){
    return this.http.get(this.url).toPromise();
  }
}

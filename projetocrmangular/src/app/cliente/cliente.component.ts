import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { error } from 'protractor';
import { map } from 'rxjs/operators';
import { Cliente } from '../models/cliente';
import { ListagemClientesComponent } from '../listagem-clientes/listagem-clientes.component';
import { FormGroup, NgForm } from '@angular/forms';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {
  formData: Cliente = new Cliente();
  submitted = false;
  errorMsg = '';
  succsessMsg = '';
  hideSuccessMessage = false;

  url = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient){}


  onSubmit(form: NgForm){
    this.submitted = true;
    this.http.post(this.url,this.formData).subscribe(
      data => this.succsessMsg = "Cliente salvo com sucesso!",
      error => this.errorMsg = error.statusText
    );

    this.hideSuccessMessage = false;
  }

  errorHandler(error: HttpErrorResponse){
    return throwError(error);
  }

  FadeOutSuccessMsg() {
     setTimeout( () => {
           this.hideSuccessMessage = true;
        }, 3000);
  }
  ngOnInit(){
  }

}

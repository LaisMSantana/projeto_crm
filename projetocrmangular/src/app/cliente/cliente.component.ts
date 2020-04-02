import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { error } from 'protractor';
import { map } from 'rxjs/operators';
import { Cliente } from '../models/cliente';
import { ListagemClientesComponent } from '../listagem-clientes/listagem-clientes.component';
import { FormGroup, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteService } from '../service/cliente.service';

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
  titulo: string;

  url = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient,
    private _route: ActivatedRoute,
    private clienteService: ClienteService,
    private router: Router){}


  onSubmit(form: NgForm){
    this.submitted = true;
    this._route.paramMap.subscribe(parameterMap =>{
      const id = +parameterMap.get('id');
    if(id == 0){
    this.http.post(this.url,this.formData).subscribe(
      data => this.succsessMsg = "Cliente salvo com sucesso!",
      error => this.errorMsg = error.statusText );
    } else{
      this.http.put<Cliente>(this.url + '/' + this.formData.idCliente, this.formData).subscribe();
      console.log(this.formData);
      this.router.navigate(['/listagem-clientes']).then(() => {
        window.location.reload();
      });
      }
    });
    this.hideSuccessMessage = false;
  }

  FadeOutSuccessMsg() {
     setTimeout( () => {
           this.hideSuccessMessage = true;
        }, 3000);
  }
  ngOnInit(){
    this._route.paramMap.subscribe(parameterMap =>{
      const id = +parameterMap.get('id');
      this.getCliente(id)
    });
  }

  getCliente(id : number){
    if(id == 0){
      this.formData = new Cliente();
      this.titulo = 'Cadastro Cliente';
    } else {
      this.http.get(this.url + '/cliente/' + id).subscribe((data: Cliente) => this.formData = data);
      this.titulo = 'Editar Cliente';
  }
}

}

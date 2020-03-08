import { Component } from '@angular/core';
import { Cliente } from './cliente';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { error } from 'protractor';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  clienteModel = new Cliente("","","","");
  submitted = false;
  errorMsg = '';
  succsessMsg = ''
  hideSuccessMessage = false;

  url = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient){}


  onSubmit(cliente){
    this.submitted = true;
    this.http.post<any>(this.url,cliente).pipe(catchError(this.errorHandler)).subscribe(
      data => this.succsessMsg = "Cliente salvo com sucesso!",
      error => this.errorMsg = error.statusText
    )
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

}





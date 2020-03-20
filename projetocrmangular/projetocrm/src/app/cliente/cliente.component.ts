import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { error } from 'protractor';
import { map } from 'rxjs/operators';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {
  clienteModel = new Cliente(null,"","","","");
  submitted = false;
  errorMsg = '';
  succsessMsg = ''
  hideSuccessMessage = false;
  selectedFile = null;

  url = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient){}


  onSubmit(cliente){
    this.submitted = true;
    this.http.post<any>(this.url,cliente).pipe(catchError(this.errorHandler)).subscribe(
      data => this.succsessMsg = "Cliente salvo com sucesso!",
      error => this.errorMsg = error.statusText
    );

    // const fd = new FormData();
    // fd.append('image', this.selectedFile, this.selectedFile.name);
    // this.http.post(this.url, fd).subscribe( res => console.log(res));

    this.hideSuccessMessage = false;


  }

  // onFileSelected(event){
  //   this.selectedFile = <File> event.target.files[0];
  // }

  errorHandler(error: HttpErrorResponse){
    return throwError(error);
  }

  FadeOutSuccessMsg() {
     setTimeout( () => {
           this.hideSuccessMessage = true;
        }, 3000);
  }
  ngOnInit(): void {
  }

}

import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { error } from 'protractor';
import { map } from 'rxjs/operators';
import { ProdutoModel } from '../models/produto-model';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {

  produtoModel = new ProdutoModel(null,"","","","");
  submitted = false;
  errorMsg = '';
  succsessMsg = '';
  hideSuccessMessage = false;
  selectedFile = null;

  url = 'http://localhost:8080/api/produtos';

  constructor(private http: HttpClient){}


  onSubmit(produto){
    this.submitted = true;
    this.http.post<any>(this.url,produto).pipe(catchError(this.errorHandler)).subscribe(
      data => this.succsessMsg = "Produto salvo com sucesso!",
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


  ngOnInit(): void {
  }

}

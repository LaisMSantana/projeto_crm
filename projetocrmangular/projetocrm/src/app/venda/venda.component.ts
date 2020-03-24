import { Component, OnInit } from '@angular/core';
import { VendaModel } from '../venda-model';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-venda',
  templateUrl: './venda.component.html',
  styleUrls: ['./venda.component.css']
})
export class VendaComponent implements OnInit {

  vendaModel = new VendaModel(null, "", "", 0);
  itemProduto : any = [];
  submitted = false;
  errorMsg = '';
  succsessMsg = ''
  hideSuccessMessage = false;

  url = 'http://localhost:8080/api/vendas';

  constructor(private http: HttpClient){}

  onSubmit(venda){
    this.submitted = true;
    this.http.post<any>(this.url,venda).pipe(catchError(this.errorHandler)).subscribe(
      data => this.succsessMsg = "Produto salvo com sucesso!",
      error => this.errorMsg = error.statusText
    );

    this.hideSuccessMessage = false;
  }
  AddouEditarItem(itemProdutoIndex, idVenda){

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

import { Component } from '@angular/core';
import { Cliente } from './cliente';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { error } from 'protractor';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  clienteModel = new Cliente(null,"","","","");
  submitted = false;
  errorMsg = '';
  succsessMsg = ''
  hideSuccessMessage = false;
  headElements = ['Nome', 'CPF', 'Email'];
  clientes: any = [];
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

    this.ngOnInit;
    this.hideSuccessMessage = false;


  }
  ngOnInit(){
      return this.http.get(this.url).subscribe((data: any[]) =>{
        this.clientes = data;
        console.log(this.clientes);
      });
  }

  selecionarClientePorId(cliente){
    return this.http.put(this.url, cliente).subscribe((data) =>{
      console.log(data);
      this.clientes = data;
    });
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

}





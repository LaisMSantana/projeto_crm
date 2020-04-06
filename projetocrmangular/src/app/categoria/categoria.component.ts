import { Component, OnInit } from '@angular/core';
import { Categoria } from '../models/categoria';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {
  formData: Categoria = new Categoria();
  headElements = ['Categoria', ' '];
  categorias: any = [];
  editField: string;
  submitted = false;
  errorMsg = '';
  succsessMsg = '';
  hideSuccessMessage = false;
  titulo: string;

  url = 'http://localhost:8080/api/categorias';

  constructor(private http: HttpClient,
    private _route: ActivatedRoute,
    private router: Router){}


  onSubmit(form: NgForm){
    this.submitted = true;
    this._route.paramMap.subscribe(parameterMap =>{
      const id = +parameterMap.get('id');
    if(id == 0){
    this.http.post(this.url,this.formData).subscribe(
      data => this.succsessMsg = "Categoria salva com sucesso!",
      error => this.errorMsg = error.statusText );
    } else{
      this.http.put<Categoria>(this.url + '/' + this.formData.idCategoria, this.formData).subscribe();
      console.log(this.formData);
      this.router.navigate(['/categoria']).then(() => {
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
      this.getCategoria(id)
    });
    return this.http.get(this.url).subscribe((data: any[]) =>{
      this.categorias = data;
      console.log(this.categorias);
    });

  }

  getCategoria(id : number){
    if(id == 0){
      this.formData = new Categoria();
      this.titulo = 'Cadastro Categoria';
    } else {
      this.http.get(this.url + '/categoria/' + id).subscribe((data: Categoria) => this.formData = data);
      this.titulo = 'Editar Categoria';
  }
}
applyFilter(filterValue: string){
  return this.http.get(this.url + '/' +filterValue.trim().toLocaleLowerCase()).subscribe((data) =>{
    this.categorias = data;
  });
}

editarItem(categoria: Categoria){
  this.router.navigate(['/categoria', categoria.idCategoria]);
}

}

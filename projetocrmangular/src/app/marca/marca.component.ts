import { Component, OnInit } from '@angular/core';
import { Marca } from '../models/marca';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css']
})
export class MarcaComponent implements OnInit {
  formData: Marca = new Marca();
  headElements = ['Marca', ' '];
  marcas: any = [];
  editField: string;
  submitted = false;
  errorMsg = '';
  succsessMsg = '';
  hideSuccessMessage = false;
  titulo: string;

  url = 'http://localhost:8080/api/marcas';

  constructor(private http: HttpClient,
    private _route: ActivatedRoute,
    private router: Router){}


  onSubmit(form: NgForm){
    this.submitted = true;
    this._route.paramMap.subscribe(parameterMap =>{
      const id = +parameterMap.get('id');
    if(id == 0){
    this.http.post(this.url,this.formData).subscribe(
      data => this.succsessMsg = "Marca salva com sucesso!",
      error => this.errorMsg = error.statusText);
    } else{
      this.http.put<Marca>(this.url + '/' + this.formData.idMarca, this.formData).subscribe();
      console.log(this.formData);
      this.router.navigate(['/marca']).then(() => {
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
      this.getMarca(id)
    });
    return this.http.get(this.url).subscribe((data: any[]) =>{
      this.marcas = data;
      console.log(this.marcas);
    });
  }

  getMarca(id : number){
    if(id == 0){
      this.formData = new Marca();
      this.titulo = 'Marca';
    } else {
      this.http.get(this.url + '/marca/' + id).subscribe((data: Marca) => this.formData = data);
      this.titulo = 'Editar Marca';
  }
}
applyFilter(filterValue: string){
  return this.http.get(this.url + '/' +filterValue.trim().toLocaleLowerCase()).subscribe((data) =>{
    this.marcas = data;
  });
}

editarItem(marca: Marca){
  this.router.navigate(['/marca', marca.idMarca]);
}


}

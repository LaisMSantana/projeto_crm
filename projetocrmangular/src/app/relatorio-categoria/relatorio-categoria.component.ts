import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CategoriaService } from '../service/categoria.service';
import { Categoria } from '../models/categoria';
import { VendaModel } from '../models/venda-model';

@Component({
  selector: 'app-relatorio-categoria',
  templateUrl: './relatorio-categoria.component.html',
  styleUrls: ['./relatorio-categoria.component.css']
})
export class RelatorioCategoriaComponent implements OnInit {

  vendas: VendaModel[] = new Array<VendaModel>();
  listaCategorias: any = [];
  isDataUnicaSelected: boolean;
  dataInicial: any;
  dataFinal: any;
  categoriaSelecionada: any;


  url = 'http://localhost:8080/api/relatorios';

  constructor(private http: HttpClient,
    private router: Router,
    private categoriaService: CategoriaService){}


  ngOnInit(){
    this.categoriaService.getListaCategorias().then(res => this.listaCategorias = res as Array<Categoria>);
}

selectInput(event) {
  let selected = event.target.value;
  if (selected == "dataUnica") {
    this.isDataUnicaSelected = false;
  } else {
    this.isDataUnicaSelected = true;
  }
}

resultadoRelatorio(){
  var body ={
    ... this.dataInicial,
    ... this.dataFinal,
    ... this.categoriaSelecionada
  }
  return this.http.get(this.url + '/categoria', body).subscribe((data: any = []) =>{
    this.vendas = data;
  });
}

}

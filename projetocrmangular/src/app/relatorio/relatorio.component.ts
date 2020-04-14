import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { MarcaService } from '../service/marca.service';
import { CategoriaService } from '../service/categoria.service';
import { Marca } from '../models/marca';
import { Categoria } from '../models/categoria';

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.css']
})
export class RelatorioComponent implements OnInit {

  headElements = ['Cliente','Forma de Pagamento', 'Valor', 'Data'];
  vendas: any = [];
  listaMarcas: any = [];
  listaCategorias: any = [];
  isDataUnicaSelected: boolean;
  dataInicial: any;
  dataFinal: any;
  categoriaSelecionada: any;
  marcaSelecionada: any;


  url = 'http://localhost:8080/api/vendas';

  constructor(private http: HttpClient,
    private router: Router,
    private marcaService: MarcaService,
    private categoriaService: CategoriaService,){}


  ngOnInit(){
    this.marcaService.getListaMarcas().then(res => this.listaMarcas = res as Array<Marca>);
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

}

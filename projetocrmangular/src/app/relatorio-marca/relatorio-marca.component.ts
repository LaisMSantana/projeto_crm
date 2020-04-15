import { Component, OnInit } from '@angular/core';
import { MarcaService } from '../service/marca.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Marca } from '../models/marca';
import { VendaModel } from '../models/venda-model';

@Component({
  selector: 'app-relatorio-marca',
  templateUrl: './relatorio-marca.component.html',
  styleUrls: ['./relatorio-marca.component.css']
})
export class RelatorioMarcaComponent implements OnInit {

  vendas: VendaModel[] = new Array<VendaModel>();
  listaMarcas: any = [];
  isDataUnicaSelected: boolean;
  dataInicial: any;
  dataFinal: any;
  marcaSelecionada: any;


  url = 'http://localhost:8080/api/relatorios';

  constructor(private http: HttpClient,
    private router: Router,
    private marcaService: MarcaService){}


  ngOnInit(){
    this.marcaService.getListaMarcas().then(res => this.listaMarcas = res as Array<Marca>);
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
    ... this.marcaSelecionada
  }
  return this.http.get(this.url + '/marca', body).subscribe((data: any = []) =>{
    this.vendas = data;
  });
}

}

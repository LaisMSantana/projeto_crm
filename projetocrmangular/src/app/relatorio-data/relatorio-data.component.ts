import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { VendaModel } from '../models/venda-model';

@Component({
  selector: 'app-relatorio-data',
  templateUrl: './relatorio-data.component.html',
  styleUrls: ['./relatorio-data.component.css']
})
export class RelatorioDataComponent implements OnInit {

  headElements = ['Cliente','Forma de Pagamento', 'Valor', 'Data'];
  vendas: VendaModel[] = new Array<VendaModel>();
  isDataUnicaSelected: boolean;
  dataInicial: string;
  dataFinal: string;


  url = 'http://localhost:8080/api/relatorios';

  constructor(private http: HttpClient,
    private router: Router){}


  ngOnInit(){
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
  var body = {
    "headers" : {
    dataInicial : this.dataInicial,
    dataFinal : this.dataFinal
    }
  }
  console.log(body);
  this.http.get(this.url + '/data', body).subscribe((data: any = []) =>{
    this.vendas = data;
  });
}
}

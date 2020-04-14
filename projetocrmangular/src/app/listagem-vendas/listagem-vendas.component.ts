import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { VendaModel } from '../models/venda-model';

@Component({
  selector: 'app-listagem-vendas',
  templateUrl: './listagem-vendas.component.html',
  styleUrls: ['./listagem-vendas.component.css']
})
export class ListagemVendasComponent implements OnInit {

  headElements = ['Cliente','Forma de Pagamento', 'Valor', 'Data'];
  vendas: any = [];


  url = 'http://localhost:8080/api/vendas';

  constructor(private http: HttpClient,
    private router: Router){}


  ngOnInit(){
    return this.http.get(this.url).subscribe((data: any[]) =>{
      this.vendas = data;
      console.log(this.vendas);
    });

}

editarItem(venda: VendaModel){
  this.router.navigate(['/venda', venda.idVenda]);
}

cadastrarNovo(){
  this.router.navigate(['/venda']);
}
applyFilter(filtro: string){
  if(filtro.includes("/")){
    filtro = filtro.split("/").join("");
    console.log(filtro);
  }
  return this.http.get(this.url + '/' +filtro.trim().toLocaleLowerCase()).subscribe((data) =>{
    this.vendas = data;
  });
}


}

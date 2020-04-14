import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { MarcaComponent } from './marca/marca.component';
import { CategoriaComponent } from './categoria/categoria.component';
import { ListagemClientesComponent } from './listagem-clientes/listagem-clientes.component';
import { VendaComponent } from './venda/venda.component';
import { ListagemVendasComponent } from './listagem-vendas/listagem-vendas.component';
import { RelatorioComponent } from './relatorio/relatorio.component';


const routes: Routes = [
  { path: 'cliente', component: ClienteComponent},
  { path: 'cliente/:id', component: ClienteComponent},
  { path: 'marca', component: MarcaComponent},
  { path: 'marca/:id', component: MarcaComponent},
  { path: 'categoria', component: CategoriaComponent},
  { path: 'categoria/:id', component: CategoriaComponent},
  { path: 'listagem-clientes', component: ListagemClientesComponent},
  { path: 'listagem-vendas', component: ListagemVendasComponent},
  { path: 'venda', component: VendaComponent },
  { path: 'venda/:id', component: VendaComponent },
  { path: 'relatorio', component: RelatorioComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

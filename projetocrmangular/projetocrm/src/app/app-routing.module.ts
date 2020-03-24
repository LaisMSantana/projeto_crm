import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { EditarComponent } from './editar/editar.component';
import { ClienteComponent } from './cliente/cliente.component';
import { ProdutoComponent } from './produto/produto.component';
import { ListagemClientesComponent } from './listagem-clientes/listagem-clientes.component';
import { VendaComponent } from './venda/venda.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'editar', component: EditarComponent },
  { path: 'cliente', component: ClienteComponent},
  { path: 'produto', component: ProdutoComponent},
  { path: 'listagem-clientes', component: ListagemClientesComponent},
  { path: 'venda', component: VendaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

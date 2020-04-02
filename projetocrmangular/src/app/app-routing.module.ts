import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { ProdutoComponent } from './produto/produto.component';
import { ListagemClientesComponent } from './listagem-clientes/listagem-clientes.component';
import { VendaComponent } from './venda/venda.component';
import { ItemProdutoComponent } from './item-produto/item-produto.component';


const routes: Routes = [
  { path: 'cliente', component: ClienteComponent},
  { path: 'editar/:id', component: ClienteComponent},
  { path: 'produto', component: ProdutoComponent},
  { path: 'listagem-clientes', component: ListagemClientesComponent},
  { path: 'venda', component: VendaComponent },
  { path: 'item-produto', component: ItemProdutoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

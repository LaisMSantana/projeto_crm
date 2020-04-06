import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';

import { AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';

import { HomeComponent } from './home/home.component';
import { ClienteComponent } from './cliente/cliente.component';
import { ListagemClientesComponent } from './listagem-clientes/listagem-clientes.component';
import { VendaComponent } from './venda/venda.component';
import { VendaService } from './service/venda.service';
import { MarcaComponent } from './marca/marca.component';
import { CategoriaComponent } from './categoria/categoria.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ClienteComponent,
    ListagemClientesComponent,
    VendaComponent,
    MarcaComponent,
    CategoriaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    BrowserAnimationsModule,
    MatDialogModule,
    ReactiveFormsModule
  ],
  entryComponents: [],
  providers: [VendaService],
  bootstrap: [AppComponent]
})
export class AppModule { }

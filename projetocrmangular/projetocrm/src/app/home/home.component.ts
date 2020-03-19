import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  headElements = ['Nome', 'CPF', 'Email'];
  clientes: any = [];

  url = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient){}


  ngOnInit(){
    return this.http.get(this.url).subscribe((data: any[]) =>{
      this.clientes = data;
      console.log(this.clientes);
    });
}


selecionarClientePorId(id){
  return this.http.put(this.url, id).subscribe((data) =>{
    console.log(data);
    this.clientes = data;
  });
}

}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

/*
  Generated class for the EspecialidadeProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class EspecialidadeProvider {

  private baseApiPath = "http://localhost:8080/especialidade";
  private  headers: HttpHeaders = new HttpHeaders()
  .set('Content-Type', 'application/json')
  .append('Authorization', 'Bearer ' + localStorage.getItem("token"))

  constructor(public http: HttpClient) {
    console.log('Hello EspecialidadeProvider Provider');
  }

  obterEspecialidades() {
    return this.http.get(this.baseApiPath + "/listar",  {
      headers: this.headers
    });
  }


}

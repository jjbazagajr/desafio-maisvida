import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

/*
  Generated class for the CidadeProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class CidadeProvider {

  private baseApiPath = "http://localhost:8080/cidade";
  private  headers: HttpHeaders = new HttpHeaders()
  .set('Content-Type', 'application/json')
  .append('Authorization', 'Bearer ' + localStorage.getItem("token"))

  constructor(public http: HttpClient) {
    console.log('Hello Cidade Provider');
  }

  obterCidades(stateId : number) {
    console.log("stateee id: " + stateId);
    return this.http.get(this.baseApiPath + "/listar/" + stateId,{ headers: this.headers });
  }


}

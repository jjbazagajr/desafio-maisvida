import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Medico } from '../../models/medico';
import {Headers, RequestOptions} from '@angular/http'

/*
  Generated class for the MedicoProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class MedicoProvider {
  private baseApiPath = "http://localhost:8080/medico";
  private  headers: HttpHeaders = new HttpHeaders()
  .set('Content-Type', 'application/json')
  .append('Authorization', 'Bearer ' + localStorage.getItem("token"))


  constructor(public http: HttpClient) {
    console.log('Hello MedicoProvider Provider');
  }


  obterMedicos() {
    return this.http.get(this.baseApiPath + "/listar", {
      headers: this.headers
    });
  }

  cadastrarMedico(medico : Medico) {
    return this.http.post(this.baseApiPath + "/salvar", medico, {
      headers: this.headers
    });
  }

  removerMedico(medico : Medico) {
    return this.http.delete(this.baseApiPath + "/deletar/" + medico.id, {
      headers: this.headers
    });
  }

}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelper } from 'angular2-jwt';

/*
  Generated class for the AuthProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class AuthProvider {

  headers : HttpHeaders;
  responseCode : number;

  oauthTokenUrl = 'http://localhost:8080/oauth/token';
  jwtPayload : JwtHelper = new JwtHelper();

  constructor(
    private http: HttpClient,
  ) {
  }

  login(usuario: string, senha: string) {
    this.headers = new HttpHeaders();
    this.headers.set('Content-Type', 'application/x-www-form-urlencoded');
    this.headers.append('Authorization', 'Basic YW5ndWxhcjpAbmd1bEByMA==');

    const body = `username=${usuario}&password=${senha}&grant_type=password`;


    return this.http.post(this.oauthTokenUrl, body, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded')
        .append('Authorization', 'Basic YW5ndWxhcjpAbmd1bEByMA==')
    } );
   
  }
 


}

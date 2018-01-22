import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Usuario } from '../../models/usuario';
import { AuthProvider } from '../../providers/auth/auth';
import { HomePage } from '../home/home';
import { JwtHelper } from 'angular2-jwt';

/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
  providers: [
    AuthProvider,
  ]
})
export class LoginPage {

  public usuario: Usuario = new Usuario;
  public mensagemError: string;
  jwtPayload: any;
  private jwtHelper: JwtHelper = new JwtHelper();


  constructor(public navCtrl: NavController,
    public navParams: NavParams,
    public auth: AuthProvider) {
    this.carregarToken();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
    if(localStorage.getItem("token") != null) {
      this.goToHome();
    }
  }

  goToHome() {
    this.navCtrl.push(HomePage);
  }

  logar() {
    console.log("LOGIN!");
    this.auth.login(this.usuario.email, this.usuario.senha).subscribe(
      data => {
        const response = (data as any);
        console.info(response.access_token);
        this.armazenarToken(response.access_token);


        this.goToHome();


      },
      error => {

        if (error.status === 400) {
          this.mensagemError = "Usuário ou senha incorretos!"
        }
      }
    );
  }

  private armazenarToken(token: string) {
    this.jwtPayload = this.jwtHelper.decodeToken(token);
    localStorage.setItem('token', token);
  }

  private carregarToken() {
    const token = localStorage.getItem('token');

    if (token) {
      this.armazenarToken(token);
    }
  }
}

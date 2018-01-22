import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { MedicoProvider } from '../../providers/medico/medico';
import { NavParams } from 'ionic-angular/navigation/nav-params';
import { CadastroPage } from '../cadastro/cadastro';
import { Medico } from '../../models/medico';
import { MedicoDto } from '../../models/medico_dto';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
  providers: [
    MedicoProvider
  ]
})
export class HomePage {
  public novoRegistro : boolean;
  public registroRemovido : boolean;


  constructor(
    public navCtrl: NavController,
    private medicoProvider : MedicoProvider,
    public navParams : NavParams ) {
 
  }

  public listaMedicos = new Array<any>();

  ionViewDidLoad() {
    this.carregarMedicos();
  }

  ionViewWillEnter(){
  
    this.novoRegistro = CadastroPage.novoRegistro;
    this.registroRemovido = CadastroPage.registroRemovido;

    if(this.novoRegistro) {
      this.carregarMedicos();
      CadastroPage.novoRegistro = false;
    }

    if(this.registroRemovido) {
      this.carregarMedicos();
      CadastroPage.registroRemovido = false;
    }
  }

  goToRegisterPage() {
    this.navCtrl.push(CadastroPage);
  }

  goToEditPage(medico : Medico) {
    this.navCtrl.push(CadastroPage, medico);
  }


  carregarMedicos() {
    this.medicoProvider.obterMedicos().subscribe(
      data=> {
        const response = (data as any);
        const responseData = response.data;
        const doctorList = JSON.parse(JSON.stringify(responseData.content));
        const responseCode : number = response.code;
        const responseMessage : string = response.message;

        this.listaMedicos = doctorList;

        console.log(data);
        console.log("response data: " + responseData);
        console.log("Lista de médicos: " + doctorList);
        console.log("Código: " + responseCode);
        console.log("Mensagem: " + responseMessage);
      },
      error => {
        console.log(error);
      }
    );
  }

}

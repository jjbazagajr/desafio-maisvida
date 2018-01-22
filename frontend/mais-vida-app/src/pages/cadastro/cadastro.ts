import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Location } from '@angular/common';
import { Medico } from '../../models/medico';
import { Estado } from '../../models/estado';
import { EstadoProvider } from '../../providers/estado/estado';
import { EspecialidadeProvider } from '../../providers/especialidade/especialidade';
import { Cidade } from '../../models/cidade';
import { CidadeProvider } from '../../providers/cidade/cidade';
import { Address } from '../../models/address';
import { MedicoProvider } from '../../providers/medico/medico';
import { Especialidade } from '../../models/especialidade';
import { AlertController } from 'ionic-angular';
/**
 * Generated class for the CadastroPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-cadastro',
  templateUrl: 'cadastro.html',
  providers: [
    EspecialidadeProvider,
    EstadoProvider,
    MedicoProvider
  ]
})
export class CadastroPage {

  public medico : Medico = new Medico();
  public estados = new Array<Estado>();
  public cidades = new Array<Cidade>();
  public especialidades = new Array<Especialidade>();
  public estadoId : number;
  public cidadeId : number;
  public especialidadeId : number;
  public static novoRegistro : boolean;
  public static registroRemovido : boolean;
  public mensagemErro;

  constructor(public navCtrl: NavController,
     public navParams: NavParams,
     public alertCrtl : AlertController,
     private estadoProvider : EstadoProvider,
     private especialidadeProvider : EspecialidadeProvider,
     private cidadeProvider : CidadeProvider,
     private medicoProvider : MedicoProvider,
     public location : Location) {
     
       console.log(this.medico);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastroPage');
    let idMedico : Medico =    this.navParams.get("id");

    if(idMedico != null)
      this.updateAttDoctor();

    console.log("id do médico: " + idMedico);

    this.especialidadeProvider.obterEspecialidades().subscribe(
      data=> {
        const response = (data as any);
        const responseData = response.data;
        this.especialidades = JSON.parse(JSON.stringify(responseData));
        const responseCode : number = response.code;
        const responseMessage : string = response.message;

        console.log(data);
        console.log("response data: " + responseData);
        console.log("Lista de especialidades: " + this.especialidades);
        console.log("Código: " + responseCode);
        console.log("Mensagem: " + responseMessage);

        this.getStates();
      },
      error => {
        console.log(error);
      }
    );
  }

  updateAttDoctor() {
    this.medico = this.navParams.data;
    this.especialidadeId = this.medico.especialidade.id;
    this.cidadeId = this.medico.address.cidade.id;
    this.estadoId = this.medico.address.cidade.estado.id;
    this.getCities();
  }

  removerRegistro() {
    let confirm = this.alertCrtl.create({
      title: 'Remover registro',
      message: 'Tem certeza que desja excluir?',
      buttons: [
        {
          text: 'Não',
          handler: () => {
            
          }
        },
        {
          text: 'Sim',
          handler: () => {
            this.medicoProvider.removerMedico(this.medico).subscribe(
              data=> {
                const response = (data as any);
                const responseData = response.data;
                const responseCode : number = response.code;
                const responseMessage : string = response.message;
        
                console.log(data);
                console.log("response data: " + responseData);
                console.log("Código: " + responseCode);
                console.log("Mensagem: " + responseMessage);

                CadastroPage.registroRemovido = true;
                this.cancel();

              },
              error => {
                console.log(error);
              }
            );;
          }
        }
      ]
    });
    confirm.present();
}

  cancel() {
    this.navCtrl.pop();
  }

  saveDoctor() {

    console.log(this.medico);
    console.log(this.estadoId);

    this.medicoProvider.cadastrarMedico(this.medico).subscribe(
      data=> {
        const response = (data as any);
        const responseData = response.data;
        const responseCode : number = response.code;
        const responseMessage : string = response.message;

        console.log(data);
        console.log("response data: " + responseData);
        console.log("Lista de estados: " + this.estados);
        console.log("Código: " + responseCode);
        console.log("Mensagem: " + responseMessage);

        if(responseCode == 200) {
          this.navCtrl.pop();
          CadastroPage.novoRegistro = true;
        }
        if(responseCode == 400) {
  
          this.mensagemErro = responseMessage.split(",");
        }
      },
      error => {
        console.log(error);
      }
    );


  }

  replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}
  
  onChangeCity() {
    console.log("ON CHANGE CITY");

    if(this.medico.address == null) {
  
      let address : Address = new Address();
      address.cidade = new Cidade();
      this.medico.address = address;
    }
    this.medico.address.cidade.id = this.cidadeId;
  }

  onChangeEsp() {

    if(this.medico.especialidade == null) {
      let especialidade = new Especialidade();
      this.medico.especialidade = especialidade;
    }
    this.medico.especialidade.id = this.especialidadeId;
  }

  getStates() {
    this.estadoProvider.obterEstados().subscribe(
      data=> {
        const response = (data as any);
        const responseData = response.data;
        this.estados = JSON.parse(JSON.stringify(responseData));
        const responseCode : number = response.code;
        const responseMessage : string = response.message;

        console.log(data);
        console.log("response data: " + responseData);
        console.log("Lista de estados: " + this.estados);
        console.log("Código: " + responseCode);
        console.log("Mensagem: " + responseMessage);
      },
      error => {
        console.log(error);
      }
    );
  }


  getCities() {
    console.info("Estado:::: " + this.estadoId);
    this.cidadeProvider.obterCidades(this.estadoId).subscribe(
      data=> {
        const response = (data as any);
        const responseData = response.data;
        this.cidades = JSON.parse(JSON.stringify(responseData));
        const responseCode : number = response.code;
        const responseMessage : string = response.message;

        console.log(data);
        console.log("response data: " + responseData);
        console.log("Lista de cidades: " + this.cidades);
        console.log("Código: " + responseCode);
        console.log("Mensagem: " + responseMessage);
      },
      error => {
        console.log(error);
      }
    );
  }



}

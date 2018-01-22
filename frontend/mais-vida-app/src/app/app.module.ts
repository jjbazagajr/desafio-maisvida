import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { DetalhePage } from '../pages/detalhe/detalhe';
import { CadastroPage } from '../pages/cadastro/cadastro';
import { HttpModule } from "@angular/http";
import { HttpClientModule } from '@angular/common/http';
import { MedicoProvider } from '../providers/medico/medico';
import { EspecialidadeProvider } from '../providers/especialidade/especialidade';
import { EstadoProvider } from '../providers/estado/estado';
import { CidadeProvider } from '../providers/cidade/cidade';
import { AuthProvider } from '../providers/auth/auth';


@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginPage,
    DetalhePage,
    CadastroPage
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    LoginPage,
    DetalhePage,
    CadastroPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    MedicoProvider,
    HttpClientModule,
    EspecialidadeProvider,
    EstadoProvider,
    CidadeProvider,
    AuthProvider
  ]
})
export class AppModule {}

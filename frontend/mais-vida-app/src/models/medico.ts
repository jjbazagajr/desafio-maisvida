import { Address } from "./address";
import { Especialidade } from "./especialidade"

export class Medico {

    public id : Number;
    public primeiroNome : string;
    public ultimoNome : string;
    public email : string;
    public ativo : boolean;
    public ocupado : boolean;
    public especialidade : Especialidade;
    public address : Address;


    constructor() {
        
    }
}
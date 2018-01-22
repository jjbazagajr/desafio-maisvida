import { Permissao } from "./permissao";

export class Usuario {
    codigo : number;
    nome : string;
    email : string;
    senha : string;
    permissoes : Array<Permissao>;
}
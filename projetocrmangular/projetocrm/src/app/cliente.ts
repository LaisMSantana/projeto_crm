export class Cliente {
  constructor(
    public idCliente: number,
    public nome: string,
    public cpf: string,
    public email: string,
    public dataDeNascimento: string
  ) {}
}

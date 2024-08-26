public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected String emprestimo;
	
	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente para saque.");
        } else {
            saldo -= valor;
            System.out.println(String.format("Saque de %.2f realizado com sucesso.", valor));
        }
	}

	@Override
	public void depositar(double valor) {
        	saldo += valor;
        	System.out.println(String.format("Depósito de %.2f realizado com sucesso.", valor));
   	}


	@Override
	public void transferir(double valor, IConta contaDestino) {
        	if (valor > saldo) {
            System.out.println("Saldo insuficiente para transferência.");
        	} else {
            	  	this.sacar(valor);
            	  	contaDestino.depositar(valor);
            		System.out.println(String.format("Transferência de %.2f para a conta %d realizada com sucesso.", valor, contaDestino.getNumero()));
        	}
    	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getEmprestimo(){
		if (saldo <= 2000){
			return "Empréstimo não disponível";
		} else if (saldo > 2000 && saldo < 5000) {
			return "Empréstimo de 10 mil disponível";
		}else {
			return "Empréstimo de 20 mil disponível";
		}
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	protected void verificarEmprestimo(){
		System.out.println(getEmprestimo());
	}
}

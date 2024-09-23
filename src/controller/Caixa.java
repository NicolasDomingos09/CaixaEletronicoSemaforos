package controller;

import java.util.concurrent.Semaphore;

public class Caixa extends Thread{
	
	private Semaphore saque;
	private Semaphore deposito;
	private int codigo;
	private int saldo;
	private int transacao;
	private int valor;
	
	public Caixa(Semaphore saque, Semaphore deposito, int codigo, int saldo, int transacao, int valor) {
		this.saque = saque;
		this.deposito = deposito;
		this.codigo = codigo;
		this.saldo = saldo;
		this.transacao = transacao;
		this.valor = valor;
	}

	private void saque() {
		if(valor > saldo) {
			System.out.println("Saldo da conta " + codigo + " é insuficiente para saque");
		}else {
			saldo -= valor;
			System.out.println("Saque da conta: " + codigo + " efetuado com sucesso. Valor de saque: " + valor + " .Valor restante na conta: " + saldo  );
		}
	}
	
	private void deposito() {
		if(valor <= 0) {
			System.out.println("Valor de depósito é inválido");
		}else {
			saldo += valor;
			System.out.println("Depósito na conta: " + codigo + " efetuado com sucesso. Valor de depósito: " + valor + " .Valor restante na conta: " + saldo);
		}
	}
	
	@Override
	public void run() {
		try {
			if(transacao == 2) { //saque
				saque.acquire();
				saque();
			}else { //deposito
				deposito.acquire();
				deposito();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			saque.release();
			deposito.release();
		}
		
		
	}
	
}

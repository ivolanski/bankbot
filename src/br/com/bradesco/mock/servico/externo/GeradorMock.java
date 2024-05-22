package br.com.bradesco.mock.servico.externo;

import java.util.ArrayList;
import java.util.List;

public class GeradorMock {
	
	public static List<UsuarioApiMock> usuarios;
	
	static{
		usuarios = new ArrayList<UsuarioApiMock>();
		
		UsuarioApiMock u = new UsuarioApiMock();
		u.setAgencia("0000");
		u.setConta("000000");
		u.setTitularidade("1");
		u.setSenha("0000");
		u.setSaldo("R$0,00");
		u.setExtrato("Green park estacionamento R$16,00 \nChurracaria Recanto Gaucho R$42,00\nPosto de combustível R$186,00");
		usuarios.add(u);
		
		u = new UsuarioApiMock();
		u.setAgencia("1234");
		u.setConta("123456");
		u.setTitularidade("1");
		u.setSenha("1234");
		u.setSaldo("R$12,34");
		u.setExtrato("Green park estacionamento R$16,00 \nChurracaria Recanto Gaucho R$42,00\nPosto de combustível R$186,00");
		usuarios.add(u);
		
		u = new UsuarioApiMock();
		u.setAgencia("4321");
		u.setConta("654321");
		u.setTitularidade("1");
		u.setSenha("4321");
		u.setSaldo("R$43,21");
		u.setExtrato("Green park estacionamento R$16,00 \nChurracaria Recanto Gaucho R$42,00\nPosto de combustível R$186,00");
		usuarios.add(u);
		

		
	}

}

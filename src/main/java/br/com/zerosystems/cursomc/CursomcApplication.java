package br.com.zerosystems.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.zerosystems.cursomc.domain.Categoria;
import br.com.zerosystems.cursomc.domain.Cidade;
import br.com.zerosystems.cursomc.domain.Cliente;
import br.com.zerosystems.cursomc.domain.Endereco;
import br.com.zerosystems.cursomc.domain.Estado;
import br.com.zerosystems.cursomc.domain.Pagamento;
import br.com.zerosystems.cursomc.domain.PagamentoComBoleto;
import br.com.zerosystems.cursomc.domain.PagamentoComCartao;
import br.com.zerosystems.cursomc.domain.Pedido;
import br.com.zerosystems.cursomc.domain.Produto;
import br.com.zerosystems.cursomc.domain.enums.EstadoPagamento;
import br.com.zerosystems.cursomc.domain.enums.TipoCliente;
import br.com.zerosystems.cursomc.repositories.CategoriaRepository;
import br.com.zerosystems.cursomc.repositories.CidadeRepository;
import br.com.zerosystems.cursomc.repositories.ClienteRepository;
import br.com.zerosystems.cursomc.repositories.EnderecoRepository;
import br.com.zerosystems.cursomc.repositories.EstadoRepository;
import br.com.zerosystems.cursomc.repositories.PagamentoRepository;
import br.com.zerosystems.cursomc.repositories.PedidoRepository;
import br.com.zerosystems.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Informática");		
		
		Produto p1 = new Produto(null, "computADOR", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 1000.00);
		Produto p3 = new Produto(null, "Mouse", 200.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));	
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade (null,"Uberlandia", est1);
		Cidade c2 = new Cidade (null,"São paulo", est2);
		Cidade c3 = new Cidade (null,"Campinas", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c1,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "3452345", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("234234", "234243"));
		Endereco e1 = new Endereco(null, "rua asdf", "123", "324", "2", "324234", cli1, c1);
		Endereco e2 = new Endereco(null, "rua hwerwwe", "2341", "2", "2", "453333333", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2018 09:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("04/07/2018 09:32"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2018 19:39"), null);
		ped2.setPagamento(pgto2);		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
	}

}

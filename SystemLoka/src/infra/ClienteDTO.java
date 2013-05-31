package infra;

public class ClienteDTO 
{

	private String nome;
	private String cpf;
	private String cep;
	private String endereco;
	
	public ClienteDTO (String nome, String cpf, String endereco, String cep)
	{
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cep = cep;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}

package br.com.cotiinformatica.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "empresa")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idempresa")
	private Integer idEmpresa;

	@Column(name = "nomefantasia", length = 150, nullable = false)
	private String nomeFantasia;

	@Column(name = "razaosocial", length = 150, nullable = false)
	private String razaoSocial;

	@Column(name = "cnpj", length = 25, nullable = false)
	private String cnpj;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracadastro", length = 150, nullable = false)
	private Date dataHoraCadastro;

}

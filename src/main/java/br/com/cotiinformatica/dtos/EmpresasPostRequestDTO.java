package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpresasPostRequestDTO {
	
	@Pattern(regexp = "^[A-Za-zÀ-Üà-ü0-9\\s]{8,150}$", message = "Informe um nome fantasia válido de 8 a 150 caracteres.")
	@NotBlank(message = "Por favor, informe o nome fantasia da empresa.")
	private String nomeFantasia;
	
	@Pattern(regexp = "^[A-Za-zÀ-Üà-ü0-9\\s]{8,150}$", message = "Informe uma razão social válida de 8 a 150 caracteres.")
	@NotBlank(message = "Por favor, informe a razão social da empresa.")
	private String razaoSocial;
	
	@Pattern(regexp = "^[0-9]{14}$", message = "Informe um cnpj com exatamente 14 dígitos.")
	@NotBlank(message = "Por favor, informe o cnpj da empresa.")
	private String cnpj;
}

package br.com.cotiinformatica.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.EmpresasGetResponseDTO;
import br.com.cotiinformatica.dtos.EmpresasPostRequestDTO;
import br.com.cotiinformatica.dtos.EmpresasPutRequestDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.repositories.EmpresaRepository;

@RestController
@RequestMapping("/api/empresas")
public class EmpresasController {

	@Autowired
	private EmpresaRepository empresaRepository;

	@PostMapping
	public ResponseEntity<EmpresasGetResponseDTO> post(@RequestBody EmpresasPostRequestDTO dto) {

		try {

			// capturando os dados da empresa
			Empresa empresa = new Empresa();
			empresa.setRazaoSocial(dto.getRazaoSocial());
			empresa.setNomeFantasia(dto.getNomeFantasia());
			empresa.setCnpj(dto.getCnpj());
			empresa.setDataHoraCadastro(new Date());

			// cadastrando a empresa no banco de dados
			empresaRepository.save(empresa);

			// retornando a resposta
			EmpresasGetResponseDTO response = new EmpresasGetResponseDTO();
			response.setIdEmpresa(empresa.getIdEmpresa());
			response.setNomeFantasia(empresa.getNomeFantasia());
			response.setRazaoSocial(empresa.getRazaoSocial());
			response.setCnpj(empresa.getCnpj());
			response.setDataHoraCadastro(
					new SimpleDateFormat("dd/MM/yyyy HH:mm").format(empresa.getDataHoraCadastro()));

			// HTTP 201 - CREATED
			return ResponseEntity.status(201).body(parse(empresa));
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}

	@PutMapping
	public ResponseEntity<EmpresasGetResponseDTO> put(@RequestBody EmpresasPutRequestDTO dto) {

		try {

			// consultar a empresa no banco de dados através do ID
			Optional<Empresa> item = empresaRepository.findById(dto.getIdEmpresa());

			// verificar se a empresa não foi encontrada
			if (item.isEmpty()) {
				// HTTP 404 - NOT FOUND
				return ResponseEntity.status(404).body(null);
			} else {

				// capturando os dados para edição
				Empresa empresa = item.get();
				empresa.setNomeFantasia(dto.getNomeFantasia());
				empresa.setRazaoSocial(dto.getRazaoSocial());
				empresa.setCnpj(dto.getCnpj());

				// atualizando a empresa no banco de dados
				empresaRepository.save(empresa);

				// HTTP 200 - OK
				return ResponseEntity.status(200).body(parse(empresa));
			}
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}

	@DeleteMapping("{idEmpresa}")
	public ResponseEntity<EmpresasGetResponseDTO> delete(@PathVariable("idEmpresa") Integer idEmpresa) {
		try {
			// consultar a empresa no banco de dados através do ID
			Optional<Empresa> item = empresaRepository.findById(idEmpresa);

			// verificar se a empresa não foi encontrada
			if (item.isEmpty()) {
				// HTTP 404 - NOT FOUND
				return ResponseEntity.status(404).body(null);
			} else {

				// excluindo a empresa no banco de dados
				empresaRepository.delete(item.get());

				// HTTP 200 - OK
				return ResponseEntity.status(200).body(parse(item.get()));
			}
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping
	public ResponseEntity<List<EmpresasGetResponseDTO>> getAll() {
		try {

			// consultando todas as empresas no banco de dados
			List<Empresa> empresas = empresaRepository.findAll();
			if (empresas.size() == 0)
				// HTTP 204 (NO CONTENT)
				return ResponseEntity.status(204).body(null);

			// retornando os dados das empresas
			List<EmpresasGetResponseDTO> response = new ArrayList<EmpresasGetResponseDTO>();
			for (Empresa empresa : empresas) {
				response.add(parse(empresa));
			}

			// HTTP 200 - OK
			return ResponseEntity.status(200).body(response);
		}

		catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping("{idEmpresa}")
	public ResponseEntity<EmpresasGetResponseDTO> getBId(@PathVariable("idEmpresa") Integer idEmpresa) {
		try { 
			
			//consultando uma empresa através do ID 
			Optional<Empresa> item = empresaRepository 
					.findById(idEmpresa); 
			
			if(item.isEmpty()) 
						
			//HTTP 204 (NO CONTENT) 
			return ResponseEntity.status(204).body(null); 
					
			//HTTP 200 - OK 
			return ResponseEntity.status(200) .body(parse(item.get())); } catch(Exception e) { 
				
			//HTTP 500 - INTERNAL SERVER ERROR 
			return ResponseEntity.status(500).body(null);
		}
	}

	private EmpresasGetResponseDTO parse(Empresa empresa) {

		EmpresasGetResponseDTO response = new EmpresasGetResponseDTO();
		response.setIdEmpresa(empresa.getIdEmpresa());
		response.setNomeFantasia(empresa.getNomeFantasia());
		response.setRazaoSocial(empresa.getRazaoSocial());
		response.setCnpj(empresa.getCnpj());
		response.setDataHoraCadastro(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(empresa.getDataHoraCadastro()));

		return response;
	}
}

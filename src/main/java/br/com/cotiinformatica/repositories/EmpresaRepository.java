package br.com.cotiinformatica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cotiinformatica.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}

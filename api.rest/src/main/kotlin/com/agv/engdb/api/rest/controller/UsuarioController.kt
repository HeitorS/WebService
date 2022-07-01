package com.agv.engdb.api.rest.controller

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.util.UriComponentsBuilder
import com.agv.engdb.api.rest.service.UsuarioService
import org.springframework.http.ResponseEntity
import com.agv.engdb.api.rest.dto.UsuarioForm
import com.agv.engdb.api.rest.dto.UsuarioView
import org.springframework.http.HttpStatus
import javax.validation.Valid

@RestController
@RequestMapping("/usuario")
class UsuarioController (val service: UsuarioService){

    @GetMapping("/{cpf}")
    fun buscaPorCPF(@PathVariable cpf: String): UsuarioView {
        return service.buscaPorCPF(cpf);
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: UsuarioForm,uriBuilder: UriComponentsBuilder): ResponseEntity<UsuarioView> {
        val usuarioView = service.cadastrar(dto)
        val uri = uriBuilder.path("/usuario/"+usuarioView.cpf).build().toUri()
        return ResponseEntity.created(uri).body(usuarioView)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: UsuarioForm): ResponseEntity<UsuarioView> {
        val usuarioView = service.atualizar(form)
        return ResponseEntity.ok(usuarioView)
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable cpf: String) {
        service.deletar(cpf)
    }
}
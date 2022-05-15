package com.agv.engdb.api.rest.model

import com.agv.engdb.api.rest.enum.StatusTopico
import java.time.LocalDateTime

data class Topico (
        var id: Long? = null,
        val titulo: String,
        val mensagem: String,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        val curso: Curso,
        val autor: Usuario,
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        val resposta: List<Resposta> = ArrayList()
)
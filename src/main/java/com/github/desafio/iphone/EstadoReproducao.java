package com.github.desafio.iphone;

/**
 * Estado possível do reprodutor musical do iPhone.
 */
public enum EstadoReproducao {
    /** Nenhuma música em reprodução nem pausada. */
    PARADO,
    /** Uma música está tocando no momento. */
    TOCANDO,
    /** A reprodução foi pausada, mantendo a posição da faixa. */
    PAUSADO
}

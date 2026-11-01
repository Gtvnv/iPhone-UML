package com.github.desafio.iphone;

/**
 * Papel do iPhone como Aparelho Telefônico (o "phone" do anúncio original).
 *
 * Define as operações básicas de telefonia que o dispositivo deve
 * oferecer: realizar e atender chamadas.
 */
public interface AparelhoTelefonico {

    /**
     * Realiza uma ligação para o número informado.
     *
     * @param numero número de telefone; não pode ser nulo nem vazio.
     * @throws IllegalArgumentException se {@code numero} for nulo ou vazio.
     * @throws IllegalStateException    se já houver uma chamada em andamento.
     */
    void ligar(String numero);

    /**
     * Atende uma chamada em andamento.
     *
     * @throws IllegalStateException se não houver chamada para atender.
     */
    void atender();

    /** Encerra a chamada em andamento, se houver. */
    void encerrarChamada();

    /** Inicia a navegação por correio de voz / caixa postal. */
    void iniciarCorreioVoz();

    /** @return {@code true} se há uma chamada em andamento. */
    boolean emChamada();

    /** @return o número da chamada atual, ou {@code null} se não há chamada. */
    String getNumeroEmChamada();
}

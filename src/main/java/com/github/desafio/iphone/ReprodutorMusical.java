package com.github.desafio.iphone;

/**
 * Papel do iPhone como Reprodutor Musical (o "iPod" do anúncio original).
 *
 * Define as operações que todo dispositivo capaz de reproduzir música
 * deve oferecer. Qualquer classe que implemente esta interface se
 * compromete a fornecer controle de reprodução de áudio.
 */
public interface ReprodutorMusical {

    /**
     * Inicia ou retoma a reprodução da faixa selecionada.
     *
     * @throws IllegalStateException se nenhuma música estiver selecionada.
     */
    void tocar();

    /**
     * Pausa a reprodução, mantendo a posição atual da faixa.
     *
     * @throws IllegalStateException se nada estiver tocando.
     */
    void pausar();

    /**
     * Seleciona uma música a partir do seu nome ou identificador.
     *
     * @param musica nome da música; não pode ser nulo nem vazio.
     * @throws IllegalArgumentException se {@code musica} for nulo ou vazio.
     */
    void selecionarMusica(String musica);

    /** @return o estado atual da reprodução. */
    EstadoReproducao getEstadoReproducao();

    /** @return a música selecionada, ou {@code null} se nenhuma. */
    String getMusicaAtual();
}

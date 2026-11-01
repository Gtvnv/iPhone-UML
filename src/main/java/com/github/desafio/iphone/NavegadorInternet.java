package com.github.desafio.iphone;

/**
 * Papel do iPhone como Navegador na Internet (o "internet communicator"
 * do anúncio original).
 *
 * Define as operações de navegação web: abrir páginas, abrir novas abas,
 * atualizar e inspecionar a página corrente.
 */
public interface NavegadorInternet {

    /**
     * Abre a página correspondente à URL informada na aba atual.
     *
     * @param url endereço da página; não pode ser nulo nem vazio.
     * @throws IllegalArgumentException se {@code url} for nulo ou vazio.
     */
    void exibirPagina(String url);

    /** Abre uma nova aba em branco e passa a ser a aba ativa. */
    void novaAba();

    /**
     * Atualiza (recarrega) a página corrente.
     *
     * @throws IllegalStateException se não houver página aberta para atualizar.
     */
    void atualizarPagina();

    /** @return a URL da página atual, ou {@code null} se nenhuma. */
    String getPaginaAtual();

    /** @return o número de abas abertas (sempre >= 1). */
    int getQuantidadeAbas();
}

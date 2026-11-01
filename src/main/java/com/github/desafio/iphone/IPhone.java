package com.github.desafio.iphone;

/**
 * Classe que representa o iPhone.
 *
 * Conforme apresentado por Steve Jobs no lançamento original, o iPhone
 * combina três dispositivos em um só:
 * <ul>
 *   <li>um reprodutor musical (iPod);</li>
 *   <li>um aparelho telefônico (phone);</li>
 *   <li>um navegador na internet (internet communicator).</li>
 * </ul>
 *
 * Por isso a classe implementa as três interfaces correspondentes,
 * assumindo todos esses papéis simultaneamente. Diferente de uma simples
 * demonstração, cada operação mantém e valida o estado interno do
 * dispositivo (música atual, chamada em andamento, página aberta).
 */
public class IPhone implements ReprodutorMusical, AparelhoTelefonico, NavegadorInternet {

    private final String modelo;

    // Estado do reprodutor musical
    private String musicaAtual;
    private EstadoReproducao estadoReproducao = EstadoReproducao.PARADO;

    // Estado do telefone
    private String numeroEmChamada;

    // Estado do navegador
    private String paginaAtual;
    private int quantidadeAbas = 1;

    public IPhone(String modelo) {
        this.modelo = exigirTextoValido(modelo, "modelo");
    }

    public String getModelo() {
        return modelo;
    }

    // ----- ReprodutorMusical -----

    @Override
    public void selecionarMusica(String musica) {
        this.musicaAtual = exigirTextoValido(musica, "musica");
        this.estadoReproducao = EstadoReproducao.PARADO;
        System.out.println("[Música] Música selecionada: " + this.musicaAtual);
    }

    @Override
    public void tocar() {
        if (musicaAtual == null) {
            throw new IllegalStateException("Nenhuma música selecionada para tocar.");
        }
        estadoReproducao = EstadoReproducao.TOCANDO;
        System.out.println("[Música] Reproduzindo: " + musicaAtual);
    }

    @Override
    public void pausar() {
        if (estadoReproducao != EstadoReproducao.TOCANDO) {
            throw new IllegalStateException("Nada está tocando no momento.");
        }
        estadoReproducao = EstadoReproducao.PAUSADO;
        System.out.println("[Música] Reprodução pausada: " + musicaAtual);
    }

    @Override
    public EstadoReproducao getEstadoReproducao() {
        return estadoReproducao;
    }

    @Override
    public String getMusicaAtual() {
        return musicaAtual;
    }

    // ----- AparelhoTelefonico -----

    @Override
    public void ligar(String numero) {
        exigirTextoValido(numero, "numero");
        if (emChamada()) {
            throw new IllegalStateException(
                    "Já existe uma chamada em andamento com " + numeroEmChamada + ".");
        }
        this.numeroEmChamada = numero;
        System.out.println("[Telefone] Ligando para " + numero + "...");
    }

    @Override
    public void atender() {
        if (!emChamada()) {
            throw new IllegalStateException("Não há chamada para atender.");
        }
        System.out.println("[Telefone] Chamada atendida: " + numeroEmChamada);
    }

    @Override
    public void encerrarChamada() {
        if (emChamada()) {
            System.out.println("[Telefone] Chamada encerrada: " + numeroEmChamada);
            numeroEmChamada = null;
        }
    }

    @Override
    public void iniciarCorreioVoz() {
        System.out.println("[Telefone] Acessando o correio de voz...");
    }

    @Override
    public boolean emChamada() {
        return numeroEmChamada != null;
    }

    @Override
    public String getNumeroEmChamada() {
        return numeroEmChamada;
    }

    // ----- NavegadorInternet -----

    @Override
    public void exibirPagina(String url) {
        this.paginaAtual = exigirTextoValido(url, "url");
        System.out.println("[Internet] Abrindo a página: " + paginaAtual);
    }

    @Override
    public void novaAba() {
        quantidadeAbas++;
        paginaAtual = null;
        System.out.println("[Internet] Nova aba adicionada (total: " + quantidadeAbas + ").");
    }

    @Override
    public void atualizarPagina() {
        if (paginaAtual == null) {
            throw new IllegalStateException("Não há página aberta para atualizar.");
        }
        System.out.println("[Internet] Atualizando a página: " + paginaAtual);
    }

    @Override
    public String getPaginaAtual() {
        return paginaAtual;
    }

    @Override
    public int getQuantidadeAbas() {
        return quantidadeAbas;
    }

    // ----- Utilitário de validação -----

    /**
     * Garante que um texto não é nulo nem vazio (após remover espaços).
     *
     * @return o próprio texto quando válido.
     * @throws IllegalArgumentException quando nulo ou em branco.
     */
    private static String exigirTextoValido(String valor, String nomeCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    "O campo '" + nomeCampo + "' não pode ser nulo nem vazio.");
        }
        return valor;
    }
}

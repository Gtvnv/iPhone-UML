package com.github.desafio.iphone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("IPhone - os três papéis do dispositivo")
class IPhoneTest {

    private IPhone iphone;

    @BeforeEach
    void criarIPhone() {
        iphone = new IPhone("iPhone (2007)");
    }

    @Test
    @DisplayName("construtor rejeita modelo inválido")
    void construtorRejeitaModeloInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new IPhone(null));
        assertThrows(IllegalArgumentException.class, () -> new IPhone("  "));
    }

    @Nested
    @DisplayName("Reprodutor Musical")
    class ReprodutorMusicalTest {

        @Test
        @DisplayName("fluxo selecionar -> tocar -> pausar atualiza o estado")
        void fluxoDeReproducao() {
            assertEquals(EstadoReproducao.PARADO, iphone.getEstadoReproducao());

            iphone.selecionarMusica("Come Together");
            assertEquals("Come Together", iphone.getMusicaAtual());
            assertEquals(EstadoReproducao.PARADO, iphone.getEstadoReproducao());

            iphone.tocar();
            assertEquals(EstadoReproducao.TOCANDO, iphone.getEstadoReproducao());

            iphone.pausar();
            assertEquals(EstadoReproducao.PAUSADO, iphone.getEstadoReproducao());
        }

        @Test
        @DisplayName("tocar sem música selecionada lança exceção")
        void tocarSemMusica() {
            assertThrows(IllegalStateException.class, () -> iphone.tocar());
        }

        @Test
        @DisplayName("pausar sem estar tocando lança exceção")
        void pausarSemTocar() {
            iphone.selecionarMusica("Hey Jude");
            assertThrows(IllegalStateException.class, () -> iphone.pausar());
        }

        @Test
        @DisplayName("selecionar música inválida lança exceção")
        void selecionarMusicaInvalida() {
            assertThrows(IllegalArgumentException.class, () -> iphone.selecionarMusica(""));
            assertThrows(IllegalArgumentException.class, () -> iphone.selecionarMusica(null));
        }
    }

    @Nested
    @DisplayName("Aparelho Telefônico")
    class AparelhoTelefonicoTest {

        @Test
        @DisplayName("ligar inicia chamada e encerrar a finaliza")
        void cicloDeChamada() {
            assertFalse(iphone.emChamada());

            iphone.ligar("+55 11 99999-0000");
            assertTrue(iphone.emChamada());
            assertEquals("+55 11 99999-0000", iphone.getNumeroEmChamada());

            iphone.encerrarChamada();
            assertFalse(iphone.emChamada());
            assertNull(iphone.getNumeroEmChamada());
        }

        @Test
        @DisplayName("ligar com chamada já em andamento lança exceção")
        void ligarComChamadaAtiva() {
            iphone.ligar("111");
            assertThrows(IllegalStateException.class, () -> iphone.ligar("222"));
        }

        @Test
        @DisplayName("atender sem chamada lança exceção")
        void atenderSemChamada() {
            assertThrows(IllegalStateException.class, () -> iphone.atender());
        }

        @Test
        @DisplayName("ligar com número inválido lança exceção")
        void ligarNumeroInvalido() {
            assertThrows(IllegalArgumentException.class, () -> iphone.ligar(" "));
        }
    }

    @Nested
    @DisplayName("Navegador na Internet")
    class NavegadorInternetTest {

        @Test
        @DisplayName("começa com uma aba e sem página")
        void estadoInicial() {
            assertEquals(1, iphone.getQuantidadeAbas());
            assertNull(iphone.getPaginaAtual());
        }

        @Test
        @DisplayName("exibir página define a página atual")
        void exibirPagina() {
            iphone.exibirPagina("https://www.apple.com");
            assertEquals("https://www.apple.com", iphone.getPaginaAtual());
        }

        @Test
        @DisplayName("nova aba incrementa a contagem e limpa a página")
        void novaAba() {
            iphone.exibirPagina("https://www.apple.com");
            iphone.novaAba();
            assertEquals(2, iphone.getQuantidadeAbas());
            assertNull(iphone.getPaginaAtual());
        }

        @Test
        @DisplayName("atualizar sem página aberta lança exceção")
        void atualizarSemPagina() {
            assertThrows(IllegalStateException.class, () -> iphone.atualizarPagina());
        }

        @Test
        @DisplayName("exibir página com URL inválida lança exceção")
        void exibirPaginaInvalida() {
            assertThrows(IllegalArgumentException.class, () -> iphone.exibirPagina(null));
        }
    }

    @Test
    @DisplayName("o mesmo objeto atende aos três papéis (polimorfismo)")
    void polimorfismoDosTresPapeis() {
        ReprodutorMusical reprodutor = iphone;
        AparelhoTelefonico telefone = iphone;
        NavegadorInternet navegador = iphone;

        reprodutor.selecionarMusica("Let It Be");
        telefone.ligar("123");
        navegador.exibirPagina("https://dio.me");

        assertEquals("Let It Be", iphone.getMusicaAtual());
        assertTrue(iphone.emChamada());
        assertEquals("https://dio.me", iphone.getPaginaAtual());
    }
}

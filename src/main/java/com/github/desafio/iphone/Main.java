package com.github.desafio.iphone;

/**
 * Classe de demonstração do desafio.
 *
 * Exercita o iPhone em seus três papéis, mostrando que um único objeto
 * pode ser tratado como reprodutor musical, aparelho telefônico e
 * navegador de internet graças ao uso de interfaces, e que cada operação
 * respeita o estado interno do dispositivo.
 */
public class Main {

    public static void main(String[] args) {
        IPhone iphone = new IPhone("iPhone (2007)");
        System.out.println("=== Demonstração: " + iphone.getModelo() + " ===\n");

        System.out.println(">> Como Reprodutor Musical");
        ReprodutorMusical reprodutor = iphone;
        reprodutor.selecionarMusica("Sgt. Pepper's Lonely Hearts Club Band");
        reprodutor.tocar();
        reprodutor.pausar();
        System.out.println("   estado atual: " + reprodutor.getEstadoReproducao());

        System.out.println("\n>> Como Aparelho Telefônico");
        AparelhoTelefonico telefone = iphone;
        telefone.ligar("+55 11 99999-0000");
        telefone.atender();
        telefone.encerrarChamada();

        System.out.println("\n>> Como Navegador na Internet");
        NavegadorInternet navegador = iphone;
        navegador.exibirPagina("https://www.apple.com");
        navegador.novaAba();
        navegador.exibirPagina("https://www.dio.me");
        navegador.atualizarPagina();
        System.out.println("   abas abertas: " + navegador.getQuantidadeAbas());

        System.out.println("\n>> Validação e estado em ação");
        try {
            new IPhone("iPhone 15").pausar(); // nada selecionado
        } catch (IllegalStateException e) {
            System.out.println("   erro esperado: " + e.getMessage());
        }
        try {
            iphone.selecionarMusica("   "); // texto em branco
        } catch (IllegalArgumentException e) {
            System.out.println("   erro esperado: " + e.getMessage());
        }

        System.out.println("\n=== Fim da demonstração ===");
    }
}

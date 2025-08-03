package br.com.dio.forca;

import java.util.*;

public class ForcaGame {

    private final Scanner scanner = new Scanner(System.in);
    private final List<String> palavras = Arrays.asList("maça", "java", "notebook", "internet", "forca");
    private String palavraSecreta;
    private Set<Character> letrasCorretas;
    private Set<Character> letrasErradas;
    private int tentativasRestantes;

    public void iniciar() {
        palavraSecreta = escolherPalavraAleatoria();
        letrasCorretas = new HashSet<>();
        letrasErradas = new HashSet<>();
        tentativasRestantes = 6;

        System.out.println("==============================================");
        System.out.println("🎯 Bem-vindo ao Jogo da Forca!");
        System.out.println("==============================================");

        while (tentativasRestantes > 0 && !jogoCompleto()) {
            exibirEstado();
            System.out.print("Digite uma letra: ");
            String entrada = scanner.nextLine().toLowerCase();


            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("❌ Entrada inválida. Digite apenas uma letra.");
                continue;
            }

            char letra = entrada.charAt(0);

            if (palavraSecreta.indexOf(letra) >= 0) {
                letrasCorretas.add(letra);
                System.out.println("✅ Boa! A letra existe na palavra.");
            } else {
                if (!letrasErradas.contains(letra)) {
                    tentativasRestantes--;
                    letrasErradas.add(letra);
                    System.out.println("❌ Letra errada. Tentativas restantes: " + tentativasRestantes);
                } else {
                    System.out.println("⚠️ Você já tentou essa letra.");
                }
            }
        }

        if (jogoCompleto()) {
            System.out.println("\n🎉 Parabéns! Você acertou a palavra: " + palavraSecreta);
        } else {
            System.out.println("\n💀 Você perdeu! A palavra era: " + palavraSecreta);
        }

    }

    private boolean jogoCompleto() {
        for (char c : palavraSecreta.toCharArray()) {
            if (!letrasCorretas.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private void exibirEstado() {
        System.out.print("Palavra: ");
        for (char c : palavraSecreta.toCharArray()) {
            if (letrasCorretas.contains(c)) {
                System.out.print(c + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println("\nLetras erradas: " + letrasErradas);
    }

    private String escolherPalavraAleatoria() {
        Random random = new Random();
        return palavras.get(random.nextInt(palavras.size()));
    }

}


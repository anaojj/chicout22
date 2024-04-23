package br.com;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) {
        String csvFile = "produtos.csv";
        try (Scanner scanner = new Scanner(System.in)) {
            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                List<String[]> linhas = new ArrayList<>();
                try {
                    linhas = reader.readAll();
                } catch (CsvException e) {

                    e.printStackTrace();
                }

                System.out.println("Digite o nome do produto que deseja atualizar:");
                String nomeProduto = scanner.nextLine();

                List<String[]> produtosAtualizados = new ArrayList<>();
                boolean produtoEncontrado = false;

                for (String[] linha : linhas ) {
                    if (linha[0].equals(nomeProduto)) {
                        produtoEncontrado = true;

                        System.out.println("Produto encontrado!");
                        System.out.println("Digite o novo preço (ou pressione Enter para manter o preço atual):");
                        String preco = scanner.nextLine();
                        if (!preco.isEmpty()) {
                            linha[1] = preco;
                        }

                        System.out.println("Digite a nova quantidade em estoque (ou pressione Enter para manter a quantidade atual):");
                        String quantidade = scanner.nextLine();
                        if (!quantidade.isEmpty()) {
                            linha[2] = quantidade;
                        }
                    }

                    produtosAtualizados.add(linha);
                }

                if (!produtoEncontrado) {
                    System.out.println("Produto não encontrado!");
                } else {
                    try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
                        writer.writeAll(produtosAtualizados);
                        System.out.println("Produto atualizado com sucesso!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
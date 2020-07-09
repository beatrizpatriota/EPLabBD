package utils;

import objects.CEP;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class CriarQueries {

    static PropertyUtils propertyUtils = PropertyUtils.getInstance();


    public static void criarClientes() throws IOException {
        File file = new File("/home/daniel/IdeaProjects/EPLabBD/src/main/resources/queries/output/insertCliente.sql");
        if (file.createNewFile()) System.out.println("Arquivo Criado");
        else System.out.println("FAIO");
        FileWriter fileWriter = new FileWriter(file);
        ArrayList<String> listaDeNomes = new ArrayList<>();
        listaDeNomes.addAll(Arrays.asList(readDocument("input.nome").split("\n")));

        Generator gerador = new Generator();
        int tamanhoListaNomes = listaDeNomes.size();

        StringBuilder query = new StringBuilder("INSERT INTO cliente VALUES ");
        ArrayList<Integer> numeros = new ArrayList();
        for (int i = 1; i <= 5000; i++) {
            numeros.add(i);
        }
        for (int i = 1; i <= 5000; i++) {
            String nome = listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1));
            query.append("(").append(i).append(",");
            query.append("\'").append(nome).append("\'").append(",");
            query.append("\'").append(listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1))).append("\'").append(",");
            query.append("\'").append(gerador.cpf(false)).append("\'").append(",");
            query.append("\'").append(gerador.data()).append("\'").append(",");
            query.append("\'").append(gerador.email(nome.toLowerCase())).append("\'").append(",");
            query.append("\'").append(gerador.sexo()).append("\'").append(",");
            query.append("\'").append(gerador.telefone(true)).append("\'").append(",");
            query.append("\'").append(gerador.celular()).append("\'").append(",");
            query.append(numeros.get(gerador.randomiza(numeros.size()))).append(")").append(",\n");
        }
        query.append(";\n ");
        fileWriter.write(query.toString());
        fileWriter.flush();
        fileWriter.close();

    }

    private static String readDocument(String filename) {
        StringBuilder query = new StringBuilder();
        System.out.println(propertyUtils.getProperty(filename));
        try {
            FileReader fileReader = (new FileReader(propertyUtils.getProperty(filename)));
            while (fileReader.ready()) {
                query.append(Character.toChars(fileReader.read()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query.toString();
    }

    public static void criarEnderecos() throws IOException {
        File file = new File("/home/daniel/IdeaProjects/EPLabBD/src/main/resources/queries/output/insertEnderecosFILIAAAL.sql");
        if (file.createNewFile()) System.out.println("Arquivo Criado: "+ file.getPath());
        else System.out.println("FAIO");
        FileWriter fileWriter = new FileWriter(file);
        ArrayList<CEP> listaDeEnderecos = new ArrayList<>();
        Arrays.asList(readDocument("input.ceps").split("\n")).forEach(linha -> listaDeEnderecos.add(new CEP(linha)));

        Generator gerador = new Generator();
        int tamanhoListaEnderecos = listaDeEnderecos.size();

        StringBuilder query = new StringBuilder("INSERT INTO enderecos VALUES ");
        ArrayList<Integer> numeros = new ArrayList();
        for (int i = 1; i <= 500000; i++) {
            numeros.add(i);
        }
        for (int i = 1; i <= 500000; i++) {
            CEP endereco = listaDeEnderecos.get(gerador.randomiza(tamanhoListaEnderecos - 1));
            query.append("(").append(i).append(",");
            query.append("\'").append(endereco.getLogradouro()).append("\'").append(",");
            query.append(endereco.getNum()).append(",");
            query.append("\'").append(endereco.getComplemento()).append("\'").append(",");
            query.append("\'").append(endereco.getBairro()).append("\'").append(",");
            query.append("\'").append(endereco.getCidade()).append("\'").append(",");
            query.append("\'").append(endereco.getEstado()).append("\'").append(",");
            query.append("\'").append(endereco.getCep()).append("\'").append(")").append(",\n");
        }
        query.append(";");
        fileWriter.write(query.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void criarFuncionarios() throws IOException {
        File file = new File("/home/daniel/IdeaProjects/EPLabBD/src/main/resources/queries/output/insertFuncionarios.sql");
        if (file.createNewFile()) System.out.println("Arquivo Criado: "+ file.getPath());
        else System.out.println("FAIO");
        FileWriter fileWriter = new FileWriter(file);
        ArrayList<String> listaDeNomes = new ArrayList<>();
        listaDeNomes.addAll(Arrays.asList(readDocument("input.nome").split("\n")));
        Generator gerador = new Generator();
        int tamanhoListaNomes = listaDeNomes.size();

        StringBuilder query = new StringBuilder("INSERT INTO funcionario VALUES ");
        ArrayList<Integer> numeros = new ArrayList();
        for (int i = 1; i <= 500; i++) {
            numeros.add(i+20);
        }
        for (int i = 1; i <= 200; i++) {
            String nome = listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1));
            query.append("(").append(i).append(",");
            query.append(gerador.randomiza(20)+1).append(",");
            query.append("\'").append("Personal Trainer").append("\'").append(",");
            query.append("\'").append(nome).append("\'").append(",");
            query.append("\'").append(listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1))).append("\'").append(",");
            query.append("\'").append(gerador.cpf(false)).append("\'").append(",");
            query.append("\'").append(gerador.data()).append("\'").append(",");
            query.append("\'").append(gerador.email(nome.toLowerCase())).append("\'").append(",");
            query.append("\'").append(gerador.sexo()).append("\'").append(",");
            query.append("\'").append(gerador.telefone(true)).append("\'").append(",");
            query.append("\'").append(gerador.celular()).append("\'").append(",");
            query.append(numeros.remove(gerador.randomiza(numeros.size()))).append(")").append(",\n");
        }
        for (int i = 201; i <= 350; i++) {
            String nome = listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1));
            query.append("(").append(i).append(",");
            query.append(gerador.randomiza(20)+1).append(",");
            query.append("\'").append("Professor").append("\'").append(",");
            query.append("\'").append(nome).append("\'").append(",");
            query.append("\'").append(listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1))).append("\'").append(",");
            query.append("\'").append(gerador.cpf(false)).append("\'").append(",");
            query.append("\'").append(gerador.data()).append("\'").append(",");
            query.append("\'").append(gerador.email(nome.toLowerCase())).append("\'").append(",");
            query.append("\'").append(gerador.sexo()).append("\'").append(",");
            query.append("\'").append(gerador.telefone(true)).append("\'").append(",");
            query.append("\'").append(gerador.celular()).append("\'").append(",");
            query.append(numeros.get(gerador.randomiza(numeros.size()))).append(")").append(",\n");
        }
        for (int i = 351; i <= 500; i++) {
            String nome = listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1));
            query.append("(").append(i).append(",");
            query.append(gerador.randomiza(20)+1).append(",");
            query.append("\'").append("Administrativo").append("\'").append(",");
            query.append("\'").append(nome).append("\'").append(",");
            query.append("\'").append(listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1))).append("\'").append(",");
            query.append("\'").append(gerador.cpf(false)).append("\'").append(",");
            query.append("\'").append(gerador.data()).append("\'").append(",");
            query.append("\'").append(gerador.email(nome.toLowerCase())).append("\'").append(",");
            query.append("\'").append(gerador.sexo()).append("\'").append(",");
            query.append("\'").append(gerador.telefone(true)).append("\'").append(",");
            query.append("\'").append(gerador.celular()).append("\'").append(",");
            query.append(numeros.get(gerador.randomiza(numeros.size()))).append(")").append(",\n");
        }
        query.append(";");
        fileWriter.write(query.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void criarMatricula() throws IOException {
        File file = new File("/home/daniel/IdeaProjects/EPLabBD/src/main/resources/queries/output/insertMatricula.sql");
        if (file.createNewFile()) System.out.println("Arquivo Criado");
        else System.out.println("FAIO");
        FileWriter fileWriter = new FileWriter(file);

        Generator gerador = new Generator();

        StringBuilder query = new StringBuilder("INSERT INTO cliente VALUES ");
        ArrayList<Integer> idCliente = new ArrayList();
        ArrayList<Integer> idPersonal = new ArrayList();
        for (int i = 1; i <= 5000; i++) {
            idCliente.add(i);
        }
        for (int i = 1; i <= 200; i++) {
            idPersonal.add(i);
        }
        for (int i = 1; i <= 5000; i++) {
            query.append("(").append(i).append(",");
            query.append(idCliente.remove(gerador.randomiza(idCliente.size()-1))).append(",");
            query.append(idPersonal.get(gerador.randomiza(idPersonal.size()-1))).append(",");
            query.append(gerador.randomiza(6)+1).append(",");
            query.append("\'").append(gerador.data()).append("\'").append(")").append(",\n");
        }
        query.append(";\n ");
        fileWriter.write(query.toString());
        fileWriter.flush();
        fileWriter.close();

    }

    public static void criarSalas() throws IOException {
        File file = new File("/home/daniel/IdeaProjects/EPLabBD/src/main/resources/queries/output/insertSalas.sql");
        if (file.createNewFile()) System.out.println("Arquivo Criado");
        else System.out.println("FAIO");
        FileWriter fileWriter = new FileWriter(file);
        ArrayList<String> listaDeNomes = new ArrayList<>();
        listaDeNomes.addAll(Arrays.asList(readDocument("input.salas").split("\n")));

        Generator gerador = new Generator();
        int tamanhoListaNomes = listaDeNomes.size();

        StringBuilder query = new StringBuilder("INSERT INTO sala VALUES ");
        ArrayList<Integer> numeros = new ArrayList();
        for (int i = 1; i <= 20; i++) {
            numeros.add(i);
        }
        for (int i = 1; i <= 100; i++) {
            String nome = listaDeNomes.get(gerador.randomiza(tamanhoListaNomes - 1));
            query.append("(").append(i).append(",");
            query.append(numeros.get(gerador.randomiza(numeros.size()))).append(",");
            query.append("\'").append(nome).append("\'").append(",");
            query.append(gerador.randomiza(10)*5).append(")").append(",\n");
        }
        query.append(";\n ");
        fileWriter.write(query.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void criarOferecimentos() throws IOException {
        File file = new File("/home/daniel/IdeaProjects/EPLabBD/src/main/resources/queries/output/insertOferecimento.sql");
        if (file.createNewFile()) System.out.println("Arquivo Criado");
        else System.out.println("FAIO");
        FileWriter fileWriter = new FileWriter(file);

        Generator gerador = new Generator();

        StringBuilder query = new StringBuilder("INSERT INTO oferecimento VALUES ");
        ArrayList<Integer> idSala = new ArrayList();
        ArrayList<Integer> idProfessor = new ArrayList();
        ArrayList<Integer> idAula = new ArrayList();
        for (int i = 1; i <= 100; i++) {
            idSala.add(i);
        }
        for (int i = 201; i <= 350; i++) {
            idProfessor.add(i);
        }
        for (int i = 1; i <= 20; i++) {
            idAula.add(i);
        }

        for (int i = 1; i <= 100; i++) {
            query.append("(").append(i).append(",");
            query.append(idProfessor.remove(gerador.randomiza(idProfessor.size()-1))).append(",");
            query.append(idSala.remove(gerador.randomiza(idSala.size()-1))).append(",");
            query.append(idAula.get(gerador.randomiza(idAula.size()-1))).append(",");
            query.append("\'").append(gerador.data(2020)).append(" ").append(gerador.hora()).append("\'").append(")").append(",\n");
        }
        query.append(";\n ");
        fileWriter.write(query.toString());
        fileWriter.flush();
        fileWriter.close();

    }
}

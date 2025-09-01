package br.com.weslleyanunes.eventos_embu_guacu.service;

import br.com.weslleyanunes.eventos_embu_guacu.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private static final String ARQUIVO = "data/usuarios.data";
    private final List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = carregarUsuarios();
    }

    public void cadastrarUsuario(String nome, String cpf, String telefone, String email) {
        Usuario usuario = new Usuario(nome, cpf, telefone, email);
        usuarios.add(usuario);
        salvarUsuarios();
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        System.out.println("\n=== Lista de Usuários ===");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + " - " + usuarios.get(i));
        }
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        return usuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    private void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Usuario> carregarUsuarios() {
        File file = new File(ARQUIVO);
        if (!file.exists())
            return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

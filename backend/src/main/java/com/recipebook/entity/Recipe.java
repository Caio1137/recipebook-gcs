package com.recipebook.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "recipes",
        uniqueConstraints = @UniqueConstraint(name = "uk_recipe_nome", columnNames = "nome")
)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome e obrigatorio")
    @Size(min = 3, message = "Nome deve ter no minimo 3 caracteres")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull(message = "Categoria e obrigatoria")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @NotNull(message = "Tempo de preparo e obrigatorio")
    @Min(value = 1, message = "Tempo de preparo deve ser no minimo 1 minuto")
    @Column(nullable = false)
    private Integer tempoPreparo;

    @NotNull(message = "Porcoes e obrigatorio")
    @Min(value = 1, message = "Porcoes deve ser no minimo 1")
    @Column(nullable = false)
    private Integer porcoes;

    @NotEmpty(message = "Informe pelo menos 1 ingrediente")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingrediente", nullable = false)
    private List<@NotBlank(message = "Ingrediente nao pode ficar vazio") String> ingredientes = new ArrayList<>();

    @NotBlank(message = "Modo de preparo e obrigatorio")
    @Size(min = 10, message = "Modo de preparo deve ter no minimo 10 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String modoPreparo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    public Recipe() {
    }

    public Recipe(
            String nome,
            Categoria categoria,
            Integer tempoPreparo,
            Integer porcoes,
            List<String> ingredientes,
            String modoPreparo
    ) {
        this.nome = nome;
        this.categoria = categoria;
        this.tempoPreparo = tempoPreparo;
        this.porcoes = porcoes;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
    }

    @PrePersist
    void prePersist() {
        if (dataCadastro == null) {
            dataCadastro = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Integer tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Integer getPorcoes() {
        return porcoes;
    }

    public void setPorcoes(Integer porcoes) {
        this.porcoes = porcoes;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}

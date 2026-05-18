package com.recipebook.config;

import com.recipebook.entity.Categoria;
import com.recipebook.entity.Recipe;
import com.recipebook.repository.RecipeRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner carregarReceitasIniciais(RecipeRepository recipeRepository) {
        return args -> {
            if (recipeRepository.count() > 0) {
                return;
            }

            recipeRepository.saveAll(List.of(
                    new Recipe(
                            "Brigadeiro",
                            Categoria.DOCE,
                            30,
                            20,
                            List.of(
                                    "1 lata de leite condensado",
                                    "1 colher de sopa de manteiga",
                                    "3 colheres de sopa de chocolate em po",
                                    "Chocolate granulado"
                            ),
                            "Misture o leite condensado, a manteiga e o chocolate em po. Mexa em fogo medio ate desgrudar do fundo. Deixe esfriar, enrole e passe no granulado."
                    ),
                    new Recipe(
                            "Pao de Queijo",
                            Categoria.SALGADO,
                            45,
                            16,
                            List.of(
                                    "2 xicaras de polvilho doce",
                                    "1 xicara de queijo meia cura ralado",
                                    "2 ovos",
                                    "1/2 xicara de leite",
                                    "1/4 xicara de oleo"
                            ),
                            "Aqueca o leite com o oleo. Misture ao polvilho, junte ovos e queijo e modele bolinhas. Asse ate dourar."
                    ),
                    new Recipe(
                            "Suco de Maracuja",
                            Categoria.BEBIDA,
                            10,
                            4,
                            List.of(
                                    "2 maracujas",
                                    "700 ml de agua gelada",
                                    "Acucar a gosto",
                                    "Gelo"
                            ),
                            "Bata a polpa com agua no liquidificador, coe, adoce e sirva com gelo."
                    )
            ));
        };
    }
}

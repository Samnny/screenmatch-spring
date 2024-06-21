package com.samay.screenmatch.main;

import com.samay.screenmatch.model.DataSeason;
import com.samay.screenmatch.model.DataSerie;
import com.samay.screenmatch.model.Serie;
import com.samay.screenmatch.repository.SerieRepository;
import com.samay.screenmatch.service.ConsumeApi;
import com.samay.screenmatch.service.ConvertData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner sc = new Scanner(System.in);
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private ConsumeApi consumeApi = new ConsumeApi();
    private ConvertData convertData = new ConvertData();
    private List<DataSerie> dataSeries = new ArrayList<>();
    private SerieRepository serieRepository;

    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void displayMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    searchWebSeries();
                    break;
                case 2:
                    searchEpisodeSerie();
                    break;
                case 3:
                    listSeriesSearched();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void searchWebSeries() {
        DataSerie data = getDataSerie();
        Serie serie = new Serie(data);
        //dataSeries.add(data);
        serieRepository.save(serie);
        System.out.println(data);
    }

    private DataSerie getDataSerie() {
        System.out.println("Digite o nome da série para busca");
        var nameSerie = sc.nextLine();
        var json = consumeApi.getData(ADDRESS + nameSerie.replace(" ", "+") + API_KEY);
        DataSerie data = convertData.getData(json, DataSerie.class);
        return data;
    }

    private void searchEpisodeSerie(){
        DataSerie dataSerie = getDataSerie();
        List<DataSeason> seasons = new ArrayList<>();

        for (int i = 1; i <= dataSerie.totalSeasons(); i++) {
            var json = consumeApi.getData(ADDRESS + dataSerie.title().replace(" ", "+") + "&season=" + i + API_KEY);
            DataSeason dataSeason = convertData.getData(json, DataSeason.class);
            seasons.add(dataSeason);
        }
        seasons.forEach(System.out::println);
    }

    private void listSeriesSearched(){
        List<Serie> series = serieRepository.findAll();
        //series = dataSeries.stream().map(Serie::new).collect(Collectors.toList());
        series.stream().sorted(Comparator.comparing(Serie::getGender)).forEach(System.out::println);
    }
}

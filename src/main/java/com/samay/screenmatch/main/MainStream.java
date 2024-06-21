package com.samay.screenmatch.main;

import com.samay.screenmatch.model.DataEpisode;
import com.samay.screenmatch.model.DataSeason;
import com.samay.screenmatch.model.DataSerie;
import com.samay.screenmatch.model.Episode;
import com.samay.screenmatch.service.ConsumeApi;
import com.samay.screenmatch.service.ConvertData;

import java.util.*;
import java.util.stream.Collectors;

public class MainStream {

    private Scanner sc = new Scanner(System.in);
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private ConsumeApi consumeApi = new ConsumeApi();
    private ConvertData convertData = new ConvertData();

    public void displayMenu(){

        System.out.println("Digite o nome da série: ");
        String nameSerie = sc.nextLine();

        String json = consumeApi.getData(ADDRESS +
                nameSerie.replace(" ", "+") + API_KEY);
        DataSerie data = convertData.getData(json, DataSerie.class);
       // System.out.println(data);

        List<DataSeason> seasons = new ArrayList<>();
        for (int i = 1; i <= data.totalSeasons() ; i++) {
            json = consumeApi.getData(ADDRESS +
                    nameSerie.replace(" ", "+")+"&season="+ i + API_KEY);
            DataSeason dataSeason = convertData.getData(json, DataSeason.class);
            seasons.add(dataSeason);
        }
       // seasons.forEach(System.out::println);
//
//        for (int i = 0; i < data.totalSeasons(); i++) {
//            List<DataEpisode> episodes = seasons.get(i).episodes();
//            for (int j = 0; j < episodes.size() ; j++) {
//                System.out.println(episodes.get(j).title());
//            }
//        }

       // seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

//        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        lista.stream().filter(i -> i > 5).forEach(System.out::println);
//        List<String> names = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");
//
//        names.stream()
//                .sorted()
//                .limit(2)
//                .filter(n -> n.startsWith("I"))
//                .map(String::toUpperCase)
//                .forEach(System.out::println);

        List<DataEpisode> dataEpisodes = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());
               // .toList(); //IMUTAVEL - NÃO POSSO MODIFICAR A LISTA DEPOIS

//        System.out.println("Streamss: Top 10 episodios");
//        dataEpisodes
//                .stream()
//                //.filter(e -> !e.assessment().equals("N/A"))
//                .filter(e -> !e.assessment().equalsIgnoreCase("N/A"))
//               // .peek(e -> System.out.println("Primeiro filtro "+e))
//                .sorted(Comparator.comparing(DataEpisode::assessment).reversed())
//                .limit(10)
//                .map(s -> s.title().toUpperCase())
//                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream().flatMap(s -> s.episodes().stream()
                .map(d -> new Episode(s.number(), d))).collect(Collectors.toList());

        episodes.forEach(System.out::println);
//
//        System.out.println("Digite um episodio: ");
//        var partOfTitle = sc.nextLine();
//        var episodeSearched = episodes.stream().filter(e -> e.getTitle().toUpperCase()
//                .contains(partOfTitle.toUpperCase())).findFirst();
//
//        if (episodeSearched.isPresent()){
//            System.out.println("Temporada: "+episodeSearched.get().getSeason()+
//                                " Episódio: "+episodeSearched.get().getTitle());
//        }else {
//            System.out.println("Episódio não encontrado");
//        }


        //System.out.println("A partir de que ano você deseja ver os episódios? ");
       // var year = sc.nextInt();
        //sc.nextLine();

       // LocalDate dateSearch = LocalDate.of(year, 1, 1);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
//        episodes.stream().filter(e -> e.getReleaseDate() != null
//                && e.getReleaseDate().isAfter(dateSearch))
//                .forEach(e -> System.out.println(
//                        "Season: "+ e.getSeason() +
//                            " Episode: " + e.getTitle() +
//                            " Release: " + e.getReleaseDate().format(formatter)
//                ));

        Map<Integer, Double> evaluationBySeason = episodes.stream()
                .filter(e -> e.getAssessment() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getAssessment)));

        System.out.println(evaluationBySeason);

        DoubleSummaryStatistics statistics = episodes.stream().filter(e -> e.getAssessment() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getAssessment));

        System.out.println(statistics);
    }
}

package com.example.user.model;

import java.util.*;

public class MovieRecommender {
    int totalDocs;
    private Set<String> vietnameseStopWords = new HashSet<>(Arrays.asList(
            "và", "hoặc", "cũng", "với", "trong", "một", "là", "của", "được", "cho", "các",
            "để", "về", "không", "này", "ở", "những", "đã", "có", "trên", "như", "làm", "đến"
            // Thêm các stop words khác nếu cần
    ));

    private Map<String, Integer> combinedDocument = new HashMap<>();

    private List<String> removeStopWordsAndPunctuation(String[] document) {
        List<String> filteredDocument = new ArrayList<>();
        for (String term : document) {
            String cleanTerm = term.replaceAll("\\P{L}", "").toLowerCase();
            if (!vietnameseStopWords.contains(cleanTerm) && !cleanTerm.isEmpty()) {
                filteredDocument.add(cleanTerm);
            }
        }
        return filteredDocument;
    }

    private Map<String, Double> calculateTFIDF(String genre, String description, String director, String actor, List<Map<String, Double>> allDocs) {
        combinedDocument.clear();

        String[] descriptionTerms = description.split("\\s+");
        List<String> cleanedDescription = removeStopWordsAndPunctuation(descriptionTerms);
        for (String term : cleanedDescription) {
            combinedDocument.put(term, 1 );//combinedDocument.getOrDefault(term, 0) + 1);
            //System.out.print(term + " ");
        }
//        System.out.println();
//        String[] genreTerms = genre.split("\\s+");
//        List<String> cleanedGenre = removeStopWordsAndPunctuation(genreTerms);
//        for (String term : cleanedGenre) {
//            combinedDocument.put(term, 1000 );//combinedDocument.getOrDefault(term, 0) + 1);
//            System.out.print(term + " ");
//        }
//        System.out.println();
//        String[] directorTerms = director.split("\\s+");
//        List<String> cleanedDirector = removeStopWordsAndPunctuation(directorTerms);
//        for (String term : cleanedDirector) {
//            combinedDocument.put(term, 10 );//combinedDocument.getOrDefault(term, 0) + 1);
//            System.out.print(term + " ");
//        }
//        System.out.println();
//        String[] actorTerms = actor.split("\\s+");
//        List<String> cleanedActor = removeStopWordsAndPunctuation(actorTerms);
//        for (String term : cleanedActor) {
//            combinedDocument.put(term, 10 );//combinedDocument.getOrDefault(term, 0) + 1);
//            System.out.print(term + " ");
//        }
//        System.out.println();

        Map<String, Double> tfidfMap = new HashMap<>();
        //int totalDocs = allDocs.size() + 1; // Including current document
        for (Map.Entry<String, Integer> entry : combinedDocument.entrySet()) {
            String term = entry.getKey();
            int tf = entry.getValue();
            int docsWithTerm = 0;
            for (Map<String, Double> doc : allDocs) {
                if (doc.containsKey(term)) {
                    docsWithTerm++;
                }
            }
            double idf = Math.log10((double) totalDocs / (double) (docsWithTerm + 1));
            double tfidf = tf * idf;
            tfidfMap.put(term, tfidf);
        }
        return tfidfMap;
    }

    private double cosineSimilarity(Map<String, Double> vector1, Map<String, Double> vector2) {
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        if (vector1.isEmpty() || vector2.isEmpty()) {
            return 0.0;
        }

        for (Map.Entry<String, Double> entry : vector1.entrySet()) {
            String term = entry.getKey();
            double tfidf1 = entry.getValue();
            double tfidf2 = vector2.getOrDefault(term, 0.0);
            dotProduct += tfidf1 * tfidf2;
            norm1 += Math.pow(tfidf1, 2);
        }

        for (Map.Entry<String, Double> entry : vector2.entrySet()) {
            double tfidf = entry.getValue();
            norm2 += Math.pow(tfidf, 2);
        }

        if (norm1 == 0 || norm2 == 0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    public List<Movie> recommendMovies(List<Movie> movieList, List<Movie> movieWatchedByUser) {
        List<Map<String, Double>> allDocs = new ArrayList<>();
        Map<Movie, Map<String, Double>> movieTFIDFMap = new HashMap<>();
        totalDocs = movieList.size(); //+ movieWatchedByUser.size();
        for (Movie movie : movieList) {
            Map<String, Double> tfidf = calculateTFIDF(movie.getGenre(), movie.getDescription(), movie.getDirector() , movie.getActors(), allDocs);
            allDocs.add(tfidf);
            movieTFIDFMap.put(movie, tfidf);
        }

        Map<String, Double> averageTFIDF = new HashMap<>();
        int watchedCount = movieWatchedByUser.size();

        for (Movie watchedMovie : movieWatchedByUser) {
            Map<String, Double> watchedTFIDF = movieTFIDFMap.get(watchedMovie);
            if (watchedTFIDF != null) {
                for (Map.Entry<String, Double> entry : watchedTFIDF.entrySet()) {
                    averageTFIDF.put(entry.getKey(), averageTFIDF.getOrDefault(entry.getKey(), 0.0) + entry.getValue() / watchedCount);
                }
            }
        }

        movieList.sort((movie1, movie2) -> {
            double similarity1 = cosineSimilarity(movieTFIDFMap.get(movie1), averageTFIDF);
            double similarity2 = cosineSimilarity(movieTFIDFMap.get(movie2), averageTFIDF);
            return Double.compare(similarity2, similarity1);
        });
        List<Movie> result = new ArrayList<>();
        result.addAll(movieList);
        return result;
    }
}

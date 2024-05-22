package com.example.asg02.view;

import com.example.asg02.model.Movie;

import java.util.*;

public class MovieRecommender {
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

    private Map<String, Double> calculateTFIDF(String genre, String description, List<Map<String, Double>> allDocs) {
        combinedDocument.clear(); // Xóa bất kỳ nội dung trước đó của combinedDocument

        // Tách chuỗi genre và thêm các phần tử vào combinedDocument với trọng số thấp
        String[] genres = genre.split("\\s+");
        for (String g : genres) {
            combinedDocument.put(g.toLowerCase(), 1);
        }

        // Thêm description vào combinedDocument với trọng số cao
        String[] descriptionTerms = description.split("\\s+");
        List<String> cleanedDescription = removeStopWordsAndPunctuation(descriptionTerms);
        for (String term : cleanedDescription) {
            combinedDocument.put(term, combinedDocument.getOrDefault(term, 0) + 2);
        }

        // Tính toán TF-IDF cho combinedDocument
        Map<String, Double> tfidfMap = new HashMap<>();
        int totalDocs = allDocs.size();
        for (Map.Entry<String, Integer> entry : combinedDocument.entrySet()) {
            String term = entry.getKey();
            int tf = entry.getValue();
            int docsWithTerm = 0;
            for (Map<String, Double> doc : allDocs) {
                if (doc.containsKey(term)) {
                    docsWithTerm++;
                }
            }
            double idf = Math.log10((double) totalDocs / (double) (docsWithTerm + 1)); // Tránh chia cho 0
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
            return 0.0; // Kiểm tra xem nếu norm1 hoặc norm2 bằng 0 trước khi thực hiện phép chia
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
    public List<Movie> recommendMovies(List<Movie> movieList, List<Movie> movieWatchedByUser) {
        List<Map<String, Double>> allDocs = new ArrayList<>();
        Map<Movie, Map<String, Double>> movieTFIDFMap = new HashMap<>();

        for (Movie movie : movieList) {
            Map<String, Double> tfidf = calculateTFIDF(movie.getGenre(), movie.getDescription(), allDocs);
            allDocs.add(tfidf);
            movieTFIDFMap.put(movie, tfidf);
        }

        Map<String, Double> averageTFIDF = new HashMap<>();
        int watchedCount = movieWatchedByUser.size();

        for (Movie watchedMovie : movieWatchedByUser) {
            Map<String, Double> watchedTFIDF = movieTFIDFMap.get(watchedMovie);
            for (Map.Entry<String, Double> entry : watchedTFIDF.entrySet()) {
                averageTFIDF.put(entry.getKey(), averageTFIDF.getOrDefault(entry.getKey(), 0.0) + entry.getValue() / watchedCount);
            }
        }

        Collections.sort(movieList, (movie1, movie2) -> {
            double similarity1 = cosineSimilarity(movieTFIDFMap.get(movie1), averageTFIDF);
            double similarity2 = cosineSimilarity(movieTFIDFMap.get(movie2), averageTFIDF);
            return Double.compare(similarity2, similarity1);
        });
        // Output the sorted list
        return movieList;
    }
}

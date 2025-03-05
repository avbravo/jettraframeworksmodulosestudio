/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.redneuronalnino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author avbravo
 */
import java.util.*;

public class Tokenizer {
    private Map<String, Integer> tokenToIndex;
   List<String> indexToToken;

    public Tokenizer() {
        tokenToIndex = new HashMap<>();
        indexToToken = new ArrayList<>();
    }

    public void fit(List<String> codeSnippets) {
        Set<String> uniqueTokens = new HashSet<>();
        for (String snippet : codeSnippets) {
            String[] tokens = snippet.split("\\s+");
            uniqueTokens.addAll(Arrays.asList(tokens));
        }

        int index = 0;
        for (String token : uniqueTokens) {
            tokenToIndex.put(token, index);
            indexToToken.add(token);
            index++;
        }
    }

    public int[] tokenize(String codeSnippet) {
        String[] tokens = codeSnippet.split("\\s+");
        return Arrays.stream(tokens)
                     .mapToInt(token -> tokenToIndex.getOrDefault(token, -1))
                     .toArray();
    }

    public String detokenize(int[] tokenIndices) {
        StringBuilder result = new StringBuilder();
        for (int index : tokenIndices) {
            if (index >= 0 && index < indexToToken.size()) {
                result.append(indexToToken.get(index)).append(" ");
            } else {
                result.append("<UNK> ");
            }
        }
        return result.toString().trim();
    }
} 

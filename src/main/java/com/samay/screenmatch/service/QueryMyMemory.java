package com.samay.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samay.screenmatch.model.DataTranslation;

import java.net.URLEncoder;

public class QueryMyMemory {

    public static String getTranslation(String text){

        ObjectMapper mapper = new ObjectMapper();
        ConsumeApi consumeApi = new ConsumeApi();

        String textEncode = URLEncoder.encode(text);
        String langpair = URLEncoder.encode("en|pt-br");
        String url = "https://api.mymemory.translated.net/get?q=" + textEncode + "&langpair=" + langpair;;

        String json = consumeApi.getData(url);

        DataTranslation translation;
        try {
            translation = mapper.readValue(json, DataTranslation.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

        return translation.dataResponse().textTranslated();
    }
}

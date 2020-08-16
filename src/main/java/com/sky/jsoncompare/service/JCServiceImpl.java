package com.sky.jsoncompare.service;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sky.jsoncompare.helper.FlatMapUtil;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

@Service
public class JCServiceImpl implements JCService {
    @Override
    public String compareJson(String s1, String s2) {
        System.out.println("Inside json compare...");
        JsonParser parser = new JsonParser();
        try{
            Gson gson = new Gson();
            JsonElement leftJson = parser
                    .parse(new FileReader("F:\\Dev\\Java\\projects\\jsoncompare\\src\\main\\resources\\json1.txt"));
            JsonElement rightJson = parser
                    .parse(new FileReader("F:\\Dev\\Java\\projects\\jsoncompare\\src\\main\\resources\\json2.txt"));

            Type type = new TypeToken<Map<String, Object>>(){}.getType();

            Map<String, Object> leftMap = gson.fromJson(leftJson, type);
            Map<String, Object> rightMap = gson.fromJson(rightJson, type);


            Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
            Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

            MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

            System.out.println("Entries only on left\n--------------------------");
            difference.entriesOnlyOnLeft().forEach((key, value) -> System.out.println(key + ": " + value));

            System.out.println("\n\nEntries only on right\n--------------------------");
            difference.entriesOnlyOnRight().forEach((key, value) -> System.out.println(key + ": " + value));

            System.out.println("\n\nEntries differing\n--------------------------");
            difference.entriesDiffering().forEach((key, value) -> System.out.println(key + ": " + value));

            System.out.println("\n\nEntries in common\n--------------------------");
            difference.entriesInCommon().forEach((key, value) -> System.out.println(key + ": " + value));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.lilemy.airobot.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * 演员 - 电影集合
 *
 * @author lilemy
 * @date 2026-11-05
 */
@JsonPropertyOrder({"actor", "movies"})
public record ActorFilmography(String actor, List<String> movies) {
}

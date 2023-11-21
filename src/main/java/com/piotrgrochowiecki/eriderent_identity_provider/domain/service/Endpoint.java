package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.List;

@Getter
@AllArgsConstructor
public class Endpoint {

    private String uri;

    private HttpMethod method;

    //TODO zamienić to na rekord z tymi dwoma polami (i możliwie zawęzić zakres, jeśli da się to zrobić przy rekordach)
}

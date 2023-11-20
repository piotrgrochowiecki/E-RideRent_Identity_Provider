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

    static final List<Endpoint> customerAllowedEndpoints = List.of(new Endpoint("/api/v1/internal/user/uuid/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/internal/user/email/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/internal/booking/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/internal/booking/all-overlapping-with-dates/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/car/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/car/available/", HttpMethod.GET));

    static final List<Endpoint> adminAllowedEndpoints = List.of(new Endpoint("/api/v1/internal/user/uuid", HttpMethod.GET),
                                                                  new Endpoint("/api/v1/internal/user/email/", HttpMethod.GET),
                                                                  new Endpoint("/api/v1/internal/booking/", HttpMethod.GET),
                                                                  new Endpoint("/api/v1/internal/booking/all/", HttpMethod.GET),
                                                                  new Endpoint("/api/v1/internal/booking/all-overlapping-with-dates/", HttpMethod.GET),
                                                                  new Endpoint("/api/v1/car/", HttpMethod.GET),
                                                                  new Endpoint("/api/v1/car/", HttpMethod.POST),
                                                                  new Endpoint("/api/v1/car/available/", HttpMethod.GET));

    //TODO listy dostarczać z oddzielnej klasy
}

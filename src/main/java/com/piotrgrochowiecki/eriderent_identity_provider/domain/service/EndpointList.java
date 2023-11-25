package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class EndpointList {

    private final List<Endpoint> customerAllowedEndpoints = new ArrayList<>();
    private final List<Endpoint> adminAllowedEndpoints = new ArrayList<>();

    @PostConstruct
    public void init() {
        List<Endpoint> customerAllowedEndpoints = List.of(new Endpoint("/api/v1/internal/user", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/internal/user/email/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/internal/booking/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/internal/booking/all-overlapping-with-dates/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/car/", HttpMethod.GET),
                                                                       new Endpoint("/api/v1/car/available/", HttpMethod.GET));

        List<Endpoint> adminAllowedEndpoints = List.of(new Endpoint("/api/v1/internal/user", HttpMethod.GET),
                                                                    new Endpoint("/api/v1/internal/user/email/", HttpMethod.GET),
                                                                    new Endpoint("/api/v1/internal/booking/", HttpMethod.GET),
                                                                    new Endpoint("/api/v1/internal/booking/all/", HttpMethod.GET),
                                                                    new Endpoint("/api/v1/internal/booking/all-overlapping-with-dates/", HttpMethod.GET),
                                                                    new Endpoint("/api/v1/car/", HttpMethod.GET),
                                                                    new Endpoint("/api/v1/car/", HttpMethod.POST),
                                                                    new Endpoint("/api/v1/car/available/", HttpMethod.GET));

    }

}

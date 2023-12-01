package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.Role;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class RoleAndAllowedEndpointMapProvider {

    private final Map<Role, List<Endpoint>> roleAllowedEndpointListMap = new HashMap<>();

    @PostConstruct
    public void init() {
        List<Endpoint> customerAllowedEndpoints = List.of(new Endpoint("/api/v1/internal/user/uuid/", HttpMethod.GET),
                                                          new Endpoint("/api/v1/internal/booking/id", HttpMethod.GET),
                                                          new Endpoint("/api/v1/internal/booking/all-overlapping-with-dates", HttpMethod.GET),
                                                          new Endpoint("/api/v1/car/", HttpMethod.GET),
                                                          new Endpoint("/api/v1/car/available/", HttpMethod.GET));

        List<Endpoint> adminAllowedEndpoints = List.of(new Endpoint("/api/v1/internal/user/uuid/", HttpMethod.GET),
                                                       new Endpoint("/api/v1/internal/user/id/", HttpMethod.GET),
                                                       new Endpoint("/api/v1/internal/user/email/", HttpMethod.GET),
                                                       new Endpoint("/api/v1/internal/booking/id/", HttpMethod.GET),
                                                       new Endpoint("/api/v1/internal/booking/all", HttpMethod.GET),
                                                       new Endpoint("/api/v1/internal/booking/all-overlapping-with-dates", HttpMethod.GET),
                                                       new Endpoint("/api/v1/car/", HttpMethod.GET),
                                                       new Endpoint("/api/v1/car/", HttpMethod.POST),
                                                       new Endpoint("/api/v1/car/available/", HttpMethod.GET));

        roleAllowedEndpointListMap.put(Role.ADMIN, adminAllowedEndpoints);
        roleAllowedEndpointListMap.put(Role.CUSTOMER, customerAllowedEndpoints);

    }
}

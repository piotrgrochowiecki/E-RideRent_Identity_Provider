package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import org.springframework.http.HttpMethod;

record Endpoint(String uri,
                HttpMethod method) {

}

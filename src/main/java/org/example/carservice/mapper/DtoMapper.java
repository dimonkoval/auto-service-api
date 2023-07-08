package org.example.carservice.mapper;

public interface DtoMapper<P, S, Q> {
    P toModel(Q q);

    S toDto(P p);
}

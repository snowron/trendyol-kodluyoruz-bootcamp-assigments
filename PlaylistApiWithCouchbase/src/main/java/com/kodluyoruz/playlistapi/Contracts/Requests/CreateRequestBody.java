package com.kodluyoruz.playlistapi.Contracts.Requests;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@Builder
public class CreateRequestBody {
    @NonNull
    String userId;
    @NonNull
    String name;
    String description;
}


package com.kodluyoruz.playlistapi.Contracts.Requests;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateRequestBody {
    @NonNull
    String id;
    String description;
    @NonNull
    String name;


}
